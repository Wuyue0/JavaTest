package com.zixue.service;

import com.zixue.pojo.Emp;
import com.zixue.pojo.PageBean;
import com.zixue.pojo.Student;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    // 新增
    void add(Emp emp);

    // 删除
    int delete(List<Integer> ids);

    // 更新
    void edit(Emp emp);

    // 根据id查询
    Emp queryById(Integer id);

    // 分页查询
    PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    // 用户登录
    Emp login(Emp emp);
}
