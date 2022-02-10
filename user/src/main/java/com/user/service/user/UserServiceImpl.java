package com.user.service.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.user.mapper.user.UserMapper;
import com.user.pojo.user.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService{

    //cpu核数
    public static final int CPU_COUNT = 4;

    //最大线程数 (4 * 2) - 1
    public static final int MAX_THREAD_COUNT = 20;

    public CountDownLatch countDownLatch;

    @Autowired
    UserMapper userMapper;

    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;

    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }

    /**
     *
     * @param num   向本机写入num条数据
     * @return
     */
    @Override
    public String writeLocal(HttpServletResponse response,Integer num) {
        long startTime = System.currentTimeMillis();
        String path = "D:\\文档\\thread\\users.txt";
        //读取和写入数据
        int i = num / MAX_THREAD_COUNT;
        //任务集合
        List<WriteUser> tasks = new ArrayList<>();

        int skip = 0;

        for (int j = 0; j < MAX_THREAD_COUNT; j++) {
            WriteUser writeUser = new WriteUser();
            writeUser.setSkip(j * i);
            writeUser.setCount(i);
            writeUser.setPath(path);
            tasks.add(writeUser);
        }
        //在这里使用线程池执行任务
        //ThreadPoolExecutor executor = ThreadTool.get();
        countDownLatch = new CountDownLatch(tasks.size());
        for (WriteUser task : tasks) {
            threadPoolExecutor.execute(task);
            //countDownLatch.countDown();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       // System.out.println("写入完毕,耗时 ------>" + (System.currentTimeMillis() - startTime));
        return "写入完毕,路径为 : " + path + ",耗时 : " + (System.currentTimeMillis() - startTime);
    }

    @Override
    public Integer getMaxId() {
        return userMapper.getMaxId();
    }

    @Override
    public List<User> selectUsers() {
        List<User> users = userMapper.selectUsers(1,10);
        return users;
    }


    @Override
    public void insertLog(){
        //线程池
        //ThreadPoolExecutor executor = ThreadTool.get("t1");

        //ExecutorService singleExecutor = ThreadTool.getSingleExecutor();

        List<UserLog> userLogs = getUserLogs();

        for (UserLog userLog : userLogs) {

             //singleExecutor.execute(userLog);
            threadPoolExecutor.execute(userLog);


        }
    }

    /**
     *  获取一千个userlog对象
     * @return
     */
    public List<UserLog> getUserLogs(){
        List<UserLog> userLogs = new ArrayList<>();
        while (userLogs.size() < 1000){
            UserLog userLog = new UserLog();
            userLog.setName("admin" + new Date().toString());
            userLogs.add(userLog);
        }
        return userLogs;
    }


    public void writeUser(String path, List<User> users) {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileWriter = new FileWriter(path,true);
            bufferedWriter = new BufferedWriter(fileWriter);

            for (User user : users) {
                String str = "[" + user.getId() + "," + user.getName() + "]";
                bufferedWriter.write(str);
                bufferedWriter.newLine();
            }

            bufferedWriter.flush();
            bufferedWriter.close();
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<User> readUser(int skip,int count) {
        return userMapper.selectUsers(skip,count);
    }

    @Data
    class UserLog implements Runnable{

        private Long id;
        private String name;

        @Override
        public void run() {
            UserLog userLog = this;
            User user = new User();
            user.setName(userLog.getName());
            userMapper.insert(user);
        }
    }

    @Data
    class WriteUser implements Runnable{

        private int skip;

        private int count;

        private String path;



        @Override
        public void run() {
            List<User> users = readUser(skip, count);
            writeUser(path,users);
            countDownLatch.countDown();
        }


    }
}
