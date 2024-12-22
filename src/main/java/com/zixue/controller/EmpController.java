package com.zixue.controller;

import com.zixue.pojo.Emp;
import com.zixue.pojo.PageBean;
import com.zixue.pojo.Result;
import com.zixue.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

//员工
@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;

    // 分页查询
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        log.info("分页查询，参数{},{},{},{},{},{}",page,pageSize,name,gender,begin,end);
        PageBean pageBean = empService.page(page,pageSize,name,gender, begin, end);
        return Result.success(pageBean);
    }

    // 新增
    @PostMapping
    public Result insert(@RequestBody Emp emp){
        log.info("新增员工{}",emp);
        empService.add(emp);
        return Result.success(emp);
    }

    // 删除
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("删除id为{}的员工",ids);
        empService.delete(ids);
        return Result.success();
    }

    // 修改
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("修改员工{}",emp);
        empService.edit(emp);
        return Result.success(emp);
    }

    // 查询指定id的员工
    @GetMapping("/{id}")
    public Result queryById(@PathVariable Integer id){
        log.info("查询id为{}的员工",id);
        Emp emp = empService.queryById(id);
        if(emp != null)return Result.success(emp);
        return Result.success("没有找到id为"+id+"的员工");
    }
}
