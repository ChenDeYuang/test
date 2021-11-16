package com.components.mapper.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.components.pojo.user.UserCode;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserCodeMapper extends BaseMapper<UserCode> {
    @Select("select id,name,code from user_code")
    List<UserCode> selectAll();

    /*@Insert("insert into user_code (id,name,code) values (#{id},#{name},#{code})")
    int insert(UserCode userCode);*/
}
