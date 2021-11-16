package com.user.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.user.pojo.user.User;

public interface UserService extends IService<User> {
    void insert(User user);
}
