package com.scheduling.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.FieldFill;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@TableName("schedules")
@EqualsAndHashCode(callSuper = false)
public class Schedule {
    @TableId(type = IdType.AUTO)
    private Long id;
    
    @TableField("course_id")
    private Long courseId;
    
    @TableField("teacher_id")
    private Long teacherId;
    
    @TableField("classroom_id")
    private Long classroomId;
    
    @TableField("week_day")
    private Integer weekDay;
    
    @TableField("time_slot")
    private Integer timeSlot;
    
    @TableField("week_number")
    private Integer weekNumber;
    
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdTime;
    
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedTime;
    
    @TableField(exist = false)
    private Course course;
    
    @TableField(exist = false)
    private Teacher teacher;
    
    @TableField(exist = false)
    private Classroom classroom;
} 