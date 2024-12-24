package com.scheduling.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;
import java.util.List;

@Data
@TableName("teachers")
@EqualsAndHashCode(callSuper = false)
public class Teacher {
    @TableId(type = IdType.AUTO)
    private Long id;
    
    @TableField(nullable = false)
    private String name;
    
    @TableField("teacher_code")
    private String teacherCode;
    
    private String department;
    
    @TableField(nullable = false)
    @Enumerated(EnumType.STRING)
    private User.UserRole role = User.UserRole.TEACHER;  // 默认为教师角色
    
    private Boolean enabled = true;  // 默认启用
    
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdTime;
    
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedTime;
    
    @TableField(exist = false)
    private List<Course> courses;
} 