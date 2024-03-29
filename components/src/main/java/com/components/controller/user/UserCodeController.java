package com.components.controller.user;

import com.components.pojo.user.UserCode;
import com.components.service.user.UserCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usercode")
public class UserCodeController {

    @Autowired
    UserCodeService userCodeService;

    /**
     * fix bug
     * @return
     */
    @GetMapping("/selectAll")
    public List<UserCode>  selectAll(){
        List<UserCode>  list = userCodeService.selectAll();
        return list;
    }

    @GetMapping("/selectById")
    public UserCode selectById(Long id){
        return userCodeService.selectById(id);
    }

    @PostMapping
    public Boolean insert(@RequestBody UserCode userCode){
        userCodeService.insert(userCode);
        return true;
    }
}
