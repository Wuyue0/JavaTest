package com.zixue.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zixue.mapper.EmpMapper;
import com.zixue.pojo.Emp;
import com.zixue.pojo.PageBean;
import com.zixue.pojo.Student;
import com.zixue.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    EmpMapper empMapper;

    @Override
    public List<Emp> list(String name, String sex) {
        return List.of();
    }

    @Override
    public int delete(Integer id) {
        return 0;
    }

    @Override
    public void add(Emp emp) {

    }

    @Override
    public void edit(Emp emp) {

    }

    @Override
    public Student queryById(Integer id) {
        return null;
    }

    @Override
    public PageBean page(Integer page, Integer pageSize,  String name, Short gender, LocalDate begin, LocalDate end) {
//        Long count = empMapper.count(); // 总数
//        List<Emp> emps = empMapper.page((page-1)*pageSize,pageSize); // 总的对象
        PageHelper.startPage(page,pageSize);
        List<Emp> emps = empMapper.list(name,gender,begin,end);
        Page<Emp> p = (Page<Emp>) emps;
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult()); // 封装结构
        return pageBean;
    }
}
