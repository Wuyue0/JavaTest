package com.zixue.mapper;


import com.zixue.pojo.OperateLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OperateLogMapper {
    // 插入日志数值
    @Insert("insert into operate_log(operate_user,operate_time,class_name,method_name,method_params,return_value,cost_time) " +
            "values(#{operateUser},#{operateTime},#{className},#{mehtodName},#{methodParams},#{returnValue},#{costTime})")
    public void insert(OperateLog log);

}
