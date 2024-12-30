package com.zixue.service.impl;

import com.zixue.anno.Log;
import com.zixue.mapper.DeptMapper;
import com.zixue.mapper.EmpMapper;
import com.zixue.pojo.Dept;
import com.zixue.pojo.OperateLog;
import com.zixue.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpMapper  empMapper;

    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    @Transactional(rollbackFor = Exception.class) // 所有的异常都开始开启事务 不仅仅是运行异常
    @Override
    @Log
    public void delete(Integer id) {
        // 1. 根据部门id删除
        deptMapper.deleteById(id);
        int a = 1/0;
        // 2. 根据部门id 删除该部门下的员工
        empMapper.deleteByDeptId(id);
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
        Optional<Dept> result = deptMapper.list().stream().filter(d -> d.getId().equals(id)).findFirst();
        return result.orElse(null);
    }

}
