package com.user.mapper.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.user.pojo.user.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Insert("insert into user (id,name) values (#{id},#{name})")
    int insert(User user);
}
