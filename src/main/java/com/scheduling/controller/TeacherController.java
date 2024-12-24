package com.scheduling.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scheduling.entity.Teacher;
import com.scheduling.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.scheduling.common.result.Result;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
@Slf4j
public class TeacherController {
    
    @Autowired
    private TeacherService teacherService;
    
    @GetMapping("/{id}")
    public Result<Teacher> getTeacherById(@PathVariable Long id) {
        Teacher teacher = teacherService.getById(id);
        return Result.success(teacher);
    }
    
    @GetMapping("/code/{teacherCode}")
    public Result<Teacher> getTeacherByCode(@PathVariable String teacherCode) {
        Teacher teacher = teacherService.getTeacherByCode(teacherCode);
        return Result.success(teacher);
    }
    
    @GetMapping("/department/{department}")
    public Result<List<Teacher>> getTeachersByDepartment(@PathVariable String department) {
        List<Teacher> teachers = teacherService.getTeachersByDepartment(department);
        return Result.success(teachers);
    }
    
    @GetMapping("/page")
    public Result<IPage<Teacher>> pageTeachers(
            @RequestParam(defaultValue = "1") Integer current,
            @RequestParam(defaultValue = "10") Integer size) {
        Page<Teacher> page = new Page<>(current, size);
        IPage<Teacher> teacherIPage = teacherService.pageTeachers(page);
        return Result.success(teacherIPage);
    }
    
    @PostMapping
    public Result<Boolean> addTeacher(@RequestBody Teacher teacher) {
        boolean success = teacherService.addTeacher(teacher);
        return Result.success(success);
    }
    
    @PutMapping
    public Result<Boolean> updateTeacher(@RequestBody Teacher teacher) {
        boolean success = teacherService.updateTeacher(teacher);
        return Result.success(success);
    }
    
    @DeleteMapping("/{id}")
    public Result<Boolean> deleteTeacher(@PathVariable Long id) {
        boolean success = teacherService.deleteTeacher(id);
        return Result.success(success);
    }
} 