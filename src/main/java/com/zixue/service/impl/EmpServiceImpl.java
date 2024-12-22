package com.zixue.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zixue.mapper.EmpMapper;
import com.zixue.pojo.Emp;
import com.zixue.pojo.PageBean;
import com.zixue.pojo.Student;
import com.zixue.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    EmpMapper empMapper;


    @Override
    public int delete(List<Integer> ids) {
        empMapper.delete(ids);
        return 0;
    }

//    新增员工
    @Override
    public void add(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
    }

    @Override
    public void edit(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }

    @Override
    public Emp queryById(Integer id) {
        Emp emp = empMapper.queryById(id);
        return emp;
    }

    @Override
    public PageBean page(Integer page, Integer pageSize,  String name, Short gender, LocalDate begin, LocalDate end) {
        PageHelper.startPage(page,pageSize);
        List<Emp> emps = empMapper.list(name,gender,begin,end);
        Page<Emp> p = (Page<Emp>) emps;
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult()); // 封装结构
        return pageBean;
    }

    @Override
    public Emp login(Emp emp) {
        return empMapper.getUsernameAndPassword(emp);
    }
}
