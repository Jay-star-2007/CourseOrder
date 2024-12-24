package com.scheduling.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@TableName("classrooms")
@EqualsAndHashCode(callSuper = false)
public class Classroom {
    @TableId(type = IdType.AUTO)
    private Long id;
    
    @TableField("room_number")
    private String roomNumber;
    
    private Integer capacity;
    
    private String building;
    
    @TableField("room_type")
    private ClassroomType type;
    
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdTime;
    
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedTime;
    
    public enum ClassroomType {
        LECTURE_ROOM,
        COMPUTER_LAB,
        LABORATORY
    }
} 