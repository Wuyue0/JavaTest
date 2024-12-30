package com.zixue.mapper;

import com.zixue.pojo.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper {
//    //    查询所有的学生
//    @Select("<script>" +
//            "select stu.id id, stu.name name, address ,sex, dept.name deptName, dept.id deptId from student stu inner join dept on stu.dept_id = dept.id " +
//            "<if test='name != null and name != \"\"'>" +
//            "where stu.name like concat('%',#{name},'%')" +
//            "</if>" +
//            "<if test='sex != null and sex != \"\"'>"+
//            "and  stu.sex = #{sex}" +
//            "</if>" +
//            "</script> ")
    List<Student> queryStudents(String name, String sex);

   // 根据id删除某个学生
//    @Delete("delete from student where id = #{id}")
    int delete(Integer id);

    // 新增学生
//    @Options(useGeneratedKeys = true, keyProperty = "id") //获取返回的主键并填充到student中
//    @Insert("insert into student(name,address,sex,dept_id) values(#{name},#{address},#{sex},#{deptId})")
    void insert(Student student);

    // 更新学生
//    @Update("update student set name = #{name},address = #{address},sex = #{sex},dept_id = #{deptId} where id = #{id}")
    void update(Student student);

    // 根据id查询某个学生
    //@Select("select s.id id, s.name name, address ,sex, dept.name deptName, dept.id deptId from student s inner join dept on s.dept_id = dept.id where s.id = #{id}")
    Student queryStudentById(Integer id);


    // 批量删除员工
    void  deleteByIds(List<Integer> ids);
}
