package com.scheduling.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

@Data
@TableName("courses")
@EqualsAndHashCode(callSuper = false)
public class Course {
    @TableId(type = IdType.AUTO)
    private Long id;
    
    @TableField(value = "course_name", insertStrategy = FieldStrategy.NOT_NULL)
    private String courseName;
    
    @TableField(value = "credit_hours", insertStrategy = FieldStrategy.NOT_NULL)
    private Integer creditHours;
    
    @TableField(value = "course_code")
    private String courseCode;
    
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdTime;
    
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedTime;
    
    @TableField(exist = false)
    private List<Teacher> teachers;
} 