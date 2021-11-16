package com.components.service.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.components.mapper.user.UserCodeMapper;
import com.components.pojo.user.UserCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userCodeService")
public class UserCodeServiceImpl extends ServiceImpl<UserCodeMapper,UserCode> implements UserCodeService {

    @Autowired
    UserCodeMapper userCodeMapper;

    @Override
    public List<UserCode> selectAll() {
        return userCodeMapper.selectAll();
    }

    @Override
    public void insert(UserCode userCode) {
        userCodeMapper.insert(userCode);
    }

    public UserCode selectById(Long id){
        QueryWrapper<UserCode> query = new QueryWrapper<>();
        query.eq("id",id);
        UserCode userCode = userCodeMapper.selectOne(query);
        return userCode;
    }
}
