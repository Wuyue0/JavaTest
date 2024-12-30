package com.zixue.service.impl;

import com.zixue.pojo.Student;
import com.zixue.mapper.StudentMapper;
import com.zixue.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // 将当前类交给ioc容器管理 成为ioc容器的一个bean对象
public class StuServiceA implements StuService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> listStu(String name, String sex) {
        // 对数据进行逻辑处理~~~
        return studentMapper.queryStudents(name, sex);
    }

    @Override
    public int delete(Integer id) {
        return studentMapper.delete(id);
    }

    @Override
    public void insert(Student student){
        studentMapper.insert(student);
        System.out.println(student.getId());
    }

    @Override
    public void update(Student student){
        studentMapper.update(student);
    }

    @Override
    public Student queryStudentById(Integer id){
        return studentMapper.queryStudentById(id);
    }
}
