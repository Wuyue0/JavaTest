package com.zixue.controller;

import com.zixue.pojo.Emp;
import com.zixue.pojo.Result;
import com.zixue.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private EmpService  empService;

    @PostMapping("login")
    public Result login(@RequestBody Emp emp){
        log.info("用户信息{}",emp);
        Emp e = empService.login(emp);
        return e != null ? Result.success() : Result.error("用户名或者密码错误");
    }

}
