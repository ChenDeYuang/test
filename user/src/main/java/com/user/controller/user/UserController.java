package com.user.controller.user;

import com.components.feingnclient.usercode.UserCodeFeing;
import com.components.pojo.user.UserCode;
import com.user.pojo.user.User;
import com.user.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@EnableFeignClients(basePackages = "com.components.feingnclient")
public class UserController {

    @Autowired
    UserCodeFeing userCodeFeing;

    @Autowired
    UserService userService;

    @GetMapping("/selectAll")
    public List<UserCode> selectAll(){
        List<UserCode> list = userCodeFeing.selectAll();
        return list;
    }

    @PostMapping
    @Transactional
    public Boolean insert(@RequestBody User user){
        userService.insert(user);
        UserCode userCode = new UserCode();
        userCode.setId(user.getId());
        userCode.setName(user.getName());
        userCode.setCode(user.getId() + "_" + user.getName());
        userCodeFeing.insert(userCode);
        return true;
    }


}
