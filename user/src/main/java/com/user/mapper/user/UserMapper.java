package com.user.mapper.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.user.pojo.user.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Insert("insert into user (id,name) values (#{id},#{name})")
    int insert(User user);

    @Select("select * from user limit #{skip},#{count}")
    List<User> selectUsers(@Param("skip") int skip,@Param("count") int count);

    Integer getMaxId();
}
