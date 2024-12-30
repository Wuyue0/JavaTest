package com.zixue.service;

import com.zixue.pojo.Dept;

import java.util.List;

public interface DeptService {
    //查询全部部门数据
    List<Dept> list();
    //删除
    void delete(Integer id);
    // 新增
    void add(Dept dept);
    // 修改
    void edit(Dept dept);
    // 根据id查询
    Dept queryById(Integer id);
}
