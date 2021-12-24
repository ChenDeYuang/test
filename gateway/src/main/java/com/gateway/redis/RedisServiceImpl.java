package com.gateway.redis;

import com.gateway.bean.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service("redisService")
public class RedisServiceImpl implements RedisService{

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void setData(String key, String data) {
        redisTemplate.opsForValue().set(key,data);
    }

    @Override
    public String getData(String key) {
        String data = (String) redisTemplate.opsForValue().get(key);
        return data;
    }

    @Override
    public void setData(String key, UserToken userToken) {
        redisTemplate.opsForValue().set(key,userToken);
    }

    @Override
    public UserToken getUserToken(String key) {
        UserToken data = (UserToken) redisTemplate.opsForValue().get(key);
        return data;
    }
}
