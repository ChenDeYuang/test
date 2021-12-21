package com.user.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.user.pojo.user.User;

import javax.servlet.http.HttpServletResponse;

public interface UserService extends IService<User> {

    void insertLog();

    void insert(User user);

    String writeLocal(HttpServletResponse response,Integer num);

    Integer getMaxId();
}
