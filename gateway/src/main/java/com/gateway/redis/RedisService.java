package com.gateway.redis;

import com.gateway.bean.UserToken;

public interface RedisService {

    void setData(String key,String data);

    String getData(String key);

    void setData(String key, UserToken userToken);

    UserToken getUserToken(String key);
}

