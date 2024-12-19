package com.zixue.service;

import com.zixue.pojo.Student;

import java.util.List;

public interface StuService {
    // 获取学生列表
     List<Student> listStu(String name, String sex);

     // 删除某个学生
     int delete(Integer id);

     // 新增学生
     void insert(Student student);

     // 更新学生
     void update(Student student);

     // 根据id查询某个学生
     Student queryStudentById(Integer id);
}
