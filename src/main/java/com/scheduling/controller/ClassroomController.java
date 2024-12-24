package com.scheduling.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scheduling.common.result.Result;
import com.scheduling.entity.Classroom;
import com.scheduling.service.ClassroomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classrooms")
@Slf4j
public class ClassroomController {
    
    @Autowired
    private ClassroomService classroomService;
    
    @GetMapping("/{id}")
    public Result<Classroom> getClassroomById(@PathVariable Long id) {
        Classroom classroom = classroomService.getById(id);
        return Result.success(classroom);
    }
    
    @GetMapping("/number/{roomNumber}")
    public Result<Classroom> getClassroomByNumber(@PathVariable String roomNumber) {
        Classroom classroom = classroomService.getClassroomByNumber(roomNumber);
        return Result.success(classroom);
    }
    
    @GetMapping("/type/{type}")
    public Result<List<Classroom>> getClassroomsByType(@PathVariable Classroom.ClassroomType type) {
        List<Classroom> classrooms = classroomService.getClassroomsByType(type);
        return Result.success(classrooms);
    }
    
    @GetMapping("/capacity/{capacity}")
    public Result<List<Classroom>> getClassroomsByCapacity(@PathVariable Integer capacity) {
        List<Classroom> classrooms = classroomService.getClassroomsByCapacity(capacity);
        return Result.success(classrooms);
    }
    
    @GetMapping("/page")
    public Result<IPage<Classroom>> pageClassrooms(
            @RequestParam(defaultValue = "1") Integer current,
            @RequestParam(defaultValue = "10") Integer size) {
        Page<Classroom> page = new Page<>(current, size);
        IPage<Classroom> classroomIPage = classroomService.pageClassrooms(page);
        return Result.success(classroomIPage);
    }
    
    @PostMapping
    public Result<Boolean> addClassroom(@RequestBody Classroom classroom) {
        boolean success = classroomService.addClassroom(classroom);
        return Result.success(success);
    }
    
    @PutMapping
    public Result<Boolean> updateClassroom(@RequestBody Classroom classroom) {
        boolean success = classroomService.updateClassroom(classroom);
        return Result.success(success);
    }
    
    @DeleteMapping("/{id}")
    public Result<Boolean> deleteClassroom(@PathVariable Long id) {
        boolean success = classroomService.deleteClassroom(id);
        return Result.success(success);
    }
} 