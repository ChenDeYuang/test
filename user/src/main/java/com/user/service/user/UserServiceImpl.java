package com.user.service.user;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.user.mapper.user.UserMapper;
import com.user.pojo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService{

    @Autowired
    UserMapper userMapper;

    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }
}
