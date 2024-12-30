package com.zixue.mapper;

import com.zixue.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
    // 查询全部部门数据
    @Select("select * from dept")
    List<Dept> list();
    // 根据id删除
    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);
    // 新增
    @Options(useGeneratedKeys = true, keyProperty = "id") //获取返回的主键并填充到dept中
    @Insert("insert into dept(name, create_time, update_time) values(#{name}, #{createTime}, #{updateTime})")
    void insert(Dept dept);
    // 更新
    @Update("update dept set name = #{name}, update_time = #{updateTime} where id = #{id}")
    void edit(Dept dept);
}
