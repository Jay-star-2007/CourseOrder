package com.scheduling.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scheduling.common.annotation.RequireRole;
import com.scheduling.entity.Course;
import com.scheduling.entity.User;
import com.scheduling.service.CourseService;
import com.scheduling.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@Slf4j
public class CourseController {
    
    @Autowired
    private CourseService courseService;
    
    @GetMapping("/{id}")
    public Result<Course> getCourseById(@PathVariable Long id) {
        Course course = courseService.getById(id);
        return Result.success(course);
    }
    
    @GetMapping("/code/{courseCode}")
    public Result<Course> getCourseByCourseCode(@PathVariable String courseCode) {
        Course course = courseService.getCourseByCourseCode(courseCode);
        return Result.success(course);
    }
    
    @GetMapping("/search")
    public Result<List<Course>> getCoursesByName(@RequestParam String courseName) {
        List<Course> courses = courseService.getCoursesByName(courseName);
        return Result.success(courses);
    }
    
    @GetMapping("/page")
    public Result<IPage<Course>> pageCourses(
            @RequestParam(defaultValue = "1") Integer current,
            @RequestParam(defaultValue = "10") Integer size) {
        Page<Course> page = new Page<>(current, size);
        IPage<Course> courseIPage = courseService.pageCourses(page);
        return Result.success(courseIPage);
    }
    
    @PostMapping
    @RequireRole({User.UserRole.ADMIN})
    public Result<Boolean> addCourse(@RequestBody Course course) {
        boolean success = courseService.addCourse(course);
        return Result.success(success);
    }
    
    @PutMapping
    @RequireRole({User.UserRole.ADMIN})
    public Result<Boolean> updateCourse(@RequestBody Course course) {
        boolean success = courseService.updateCourse(course);
        return Result.success(success);
    }
    
    @DeleteMapping("/{id}")
    @RequireRole({User.UserRole.ADMIN})
    public Result<Boolean> deleteCourse(@PathVariable Long id) {
        boolean success = courseService.deleteCourse(id);
        return Result.success(success);
    }
} 