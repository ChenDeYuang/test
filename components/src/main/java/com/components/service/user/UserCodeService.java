package com.components.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.components.mapper.user.UserCodeMapper;
import com.components.pojo.user.UserCode;

import java.util.List;

public interface UserCodeService extends IService<UserCode> {
    List<UserCode>  selectAll();

    void insert(UserCode userCode);

    UserCode selectById(Long id);
}
