package com.zixue.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


// 部门实体类
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dept {
    private Integer id;  // 部门id
    private String name; // 部门名字
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 更新时间
}
