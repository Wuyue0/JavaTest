package com.zixue.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;


//员工实体类
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Emp {
    private Integer id;  // id
    private String username; // 用户名
    private String password;  // 密码
    private String name;  // 姓名
    private Short gender;  // 性别 1男 2女
    private String image; // 头像
    private String deptName; //部门名称
    private Short job; // 职位 1班主任 2讲师 3学工主管 4 教研主管 5咨询师
    private LocalDate entrydate; // 入职时间
    private Integer deptId; //部门id
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 更新时间
}
