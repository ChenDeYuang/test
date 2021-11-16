package com.components.feingnclient.usercode;


import com.components.pojo.user.UserCode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "components")
public interface UserCodeFeing {

    @GetMapping("/usercode/selectAll")
    List<UserCode> selectAll();

    @PostMapping("/usercode")
    Boolean insert(@RequestBody UserCode userCode);
}
