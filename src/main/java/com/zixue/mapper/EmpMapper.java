package com.zixue.mapper;

import com.zixue.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {

//    @Select("select count(*) from emp")
//    public Long count();
//
//    @Select("select * from emp limit #{start},#{pageSize}")
//    public List<Emp> page(Integer start, Integer pageSize);
    // 查询
    List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);

    // 新增

    @Options(useGeneratedKeys = true, keyProperty = "id") //获取返回的主键并填充到emp中
    @Update("insert into emp(username,name,gender,image,dept_id,job,entrydate,create_time,update_time) values(#{username}, #{name}, #{gender}, #{image}, #{deptId}, #{job}, #{entrydate}, #{createTime}, #{updateTime})")
    void insert(Emp emp);

    // 删除
    void delete(List<Integer> ids);

    // 修改
    // @Update("update emp set username = #{username},name = #{name},gender = #{gender},image = #{image},dept_id = #{deptId},job = #{job},entrydate = #{entrydate},update_time = #{updateTime} where id = #{id}")
    void update(Emp emp);

    // 根据id查询
//    @Select("Select * from emp where id = #{id}")
    Emp queryById(Integer id);

    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp getUsernameAndPassword(Emp emp);
}
