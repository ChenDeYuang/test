package com.user.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.user.pojo.user.User;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserService extends IService<User> {

    void insertLog();

    void insert(User user);

    String writeLocal(HttpServletResponse response,Integer num);

    Integer getMaxId();

    List<User> selectUsers();
}
