package com.zixue;

import com.zixue.mapper.StudentMapper;
import com.zixue.pojo.Student;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.*;

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

    @Test
    public  void genJwt(){
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("username", "wuyue");
        String jwt = Jwts.builder()
                        .setClaims(claims) // 设置payload
                        .signWith(SignatureAlgorithm.HS256, "wuyue") // 设置签名算法和签名使用的密钥
                        .setExpiration(new Date(System.currentTimeMillis() + 12*3600 * 1000))  // 设置过期时间
                        .compact();  // 生成jwt
        System.out.println(jwt);
    }

    @Test
    public void testParseJwt(){
        Claims claims = Jwts.parser()
                        .setSigningKey("wuyue")
                        .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwiZXhwIjoxNzM1MDkyMTU4LCJ1c2VybmFtZSI6Ind1eXVlIn0.2yS2h2qsbT7qAuBP12-KreMHDSo5oaxBlrhIm0RwMKA")
                        .getBody();
        System.out.println(claims);
    }

    @Autowired
    SAXReader saxReader;

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testLoader(){
        String[] beans = applicationContext.getBeanDefinitionNames();

        for (String bean : beans) {
            System.out.println(bean);
        }

        System.out.println("测试专用"+  Arrays.toString(beans));

    }

}
