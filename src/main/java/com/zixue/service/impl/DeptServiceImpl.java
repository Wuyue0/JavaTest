package com.zixue.service.impl;

import com.zixue.mapper.DeptMapper;
import com.zixue.pojo.Dept;
import com.zixue.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    @Override
    public void delete(Integer id) {
        deptMapper.deleteById(id);
    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }

    @Override
    public void edit(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.edit(dept);
    }

    @Override
    public Dept queryById(Integer id) {
        System.out.println("接收的值"+ id);
//        Dept dept = (Dept) deptMapper.list().stream().filter(d -> d.getId().equals(id));
        return null;
    }

}
