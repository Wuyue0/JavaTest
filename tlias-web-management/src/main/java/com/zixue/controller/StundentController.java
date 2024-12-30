package com.zixue.controller;

import com.zixue.pojo.Result;
import com.zixue.pojo.Student;
import com.zixue.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//控制层干的事情只是接收请求 返回数据
@RestController
public class StundentController {
    @Autowired
    private StuService stuService;

    @RequestMapping("/list")
    public Result list(String name, String sex){
        return  Result.success(stuService.listStu(name, sex));
    }

    @RequestMapping("/delete")
    public Result delete(Integer id){
        int code = stuService.delete(id);
        System.out.println(code);
        if(code == 0){
            return  Result.error("delete fail");
        }
        return Result.success();
    }

    @RequestMapping("/insert")
    public Result insert(@RequestBody Student student){
        stuService.insert(student);
        return Result.success();
    }

    @RequestMapping("/update")
    public Result update(@RequestBody Student student){
        stuService.update(student);
        return  Result.success();
    }

    @RequestMapping("/queryById")
    public Result queryById(Integer id){
        Student student = stuService.queryStudentById(id);
        return Result.success(student);
    }

}
