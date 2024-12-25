package com.scheduling.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Data
@TableName("users")
@EqualsAndHashCode(callSuper = false)
public class User {
    @TableId(type = IdType.AUTO)
    private Long id;
    
    @TableField(insertStrategy = FieldStrategy.NOT_NULL)
    private String username;
    
    @TableField(insertStrategy = FieldStrategy.NOT_NULL)
    private String password;
    
    @TableField("real_name")
    private String realName;
    
    @TableField(insertStrategy = FieldStrategy.NOT_NULL)
    @Enumerated(EnumType.STRING)
    private UserRole role;
    
    private Boolean enabled;
    
    @TableField("last_login_time")
    private LocalDateTime lastLoginTime;
    
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdTime;
    
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedTime;
    
    public enum UserRole {
        STUDENT,     // 学生
        TEACHER,     // 教师
        ADMIN       // 管理员
    }
} 