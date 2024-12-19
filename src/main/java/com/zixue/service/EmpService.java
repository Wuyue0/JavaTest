package com.zixue.service;

import com.zixue.pojo.Emp;
import com.zixue.pojo.PageBean;
import com.zixue.pojo.Student;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    // 获取
    List<Emp> list(String name, String sex);

    // 删除
    int delete(Integer id);

    // 新增
    void add(Emp emp);

    // 更新
    void edit(Emp emp);

    // 根据id查询
    Student queryById(Integer id);

    PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);
}
