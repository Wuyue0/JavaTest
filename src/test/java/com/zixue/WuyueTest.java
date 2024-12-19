package com.zixue;

import com.zixue.mapper.StudentMapper;
import com.zixue.pojo.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class WuyueTest {
    @Autowired
    private StudentMapper studentMapper;

    @Test
    public void testInsert(){
        Student wuyue = new Student();
        wuyue.setName("wuyue");
        wuyue.setAddress("超出");
        wuyue.setSex("男");
        wuyue.setDeptId("1");
         studentMapper.insert(wuyue);
    }

    @Test
    public void deleStudent(){
        studentMapper.delete(4);
    }

    @Test
    public void queryStudent(){
        Student student = studentMapper.queryStudentById(16);
        System.out.println(student);
    }

    @Test
    public void updateStudent(){
        Student wuyue = new Student();
        wuyue.setId("2");
        wuyue.setName("测试专用！！");
        studentMapper.update(wuyue);
    }

    @Test
    public void testDeleteByIds(){
        List<Integer> ids = Arrays.asList(1,2,3);
        studentMapper.deleteByIds(ids);
    }
}
