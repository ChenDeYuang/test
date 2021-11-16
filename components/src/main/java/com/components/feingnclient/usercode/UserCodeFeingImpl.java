package com.components.feingnclient.usercode;

import com.components.pojo.user.UserCode;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class UserCodeFeingImpl implements UserCodeFeing{
    @Override
    public List<UserCode> selectAll() {
        System.out.println("触发查询的熔断器");
        return null;
    }

    @Override
    public Boolean insert(UserCode userCode) {
        System.out.println("触发新增的熔断器");
        return null;
    }
}
