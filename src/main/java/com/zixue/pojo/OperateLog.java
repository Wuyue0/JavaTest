package com.zixue.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperateLog {
    private Integer id; //  id
    private Integer operateUser; // 操作用户ID
    private LocalDateTime operateTime; // 操作时间
    private   String className; // 类名
    private  String mehtodName; // 方法名
    private  String methodParams; // 方法参数
    private String returnValue; // 返回值
    private Long costTime; // 执行耗时
}
