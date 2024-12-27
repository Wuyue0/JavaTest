package com.zixue.controller;

import com.zixue.anno.Log;
import com.zixue.pojo.Dept;
import com.zixue.pojo.Result;
import com.zixue.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//部门
@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {
    @Autowired
    private DeptService deptService;

    // 查询接口
    @GetMapping
    public Result list(){
        log.info("查询所有的部门列表");
        List<Dept> deptList = deptService.list();
        return Result.success(deptList);
    }

    // 删除
    @Log
    @DeleteMapping("/{id}")
    public Result  delete(@PathVariable Integer id){
        log.info("删除id为{}的部门",id);
        deptService.delete(id);
        return Result.success();
    }

    // 新增
    @Log
    @PostMapping
    public Result insert(@RequestBody Dept dept){
        log.info("新增部门{}",dept);
        deptService.add(dept);
        return Result.success(dept);
    }
    // 修改
    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept){
        log.info("修改部门{}",dept);
        deptService.edit(dept);
        return Result.success(dept);
    }
    // 查询指定id的部门
    @GetMapping("/{id}")
    public Result queryById(@PathVariable Integer id){
        log.info("查询id为{}的部门",id);
        Dept dept = deptService.queryById(id);
        if(dept != null)return Result.success(dept);
        return Result.success("没有找到id为"+id+"的部门");
    }
}
