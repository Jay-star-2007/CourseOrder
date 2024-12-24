package com.scheduling.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.FieldFill;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("students")
public class Student {
    
    @TableId(type = IdType.AUTO)
    private Long id;
    
    @TableField("student_code")
    private String studentCode;
    
    @TableField("name")
    private String name;
    
    @TableField("gender")
    private String gender;
    
    @TableField("class_name")
    private String className;
    
    @TableField("department")
    private String department;
    
    @TableField("grade")
    private String grade;
    
    @TableField("phone")
    private String phone;
    
    @TableField("email")
    private String email;
    
    @TableField(nullable = false)
    @Enumerated(EnumType.STRING)
    private User.UserRole role = User.UserRole.STUDENT;  // 默认为学生角色
    
    private Boolean enabled = true;  // 默认启用
    
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdTime;
    
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedTime;
} 