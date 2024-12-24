package com.scheduling.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scheduling.common.annotation.RequireRole;
import com.scheduling.common.result.Result;
import com.scheduling.entity.Schedule;
import com.scheduling.entity.User;
import com.scheduling.service.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
@Slf4j
public class ScheduleController {
    
    @Autowired
    private ScheduleService scheduleService;
    
    @GetMapping("/{id}")
    public Result<Schedule> getScheduleById(@PathVariable Long id) {
        Schedule schedule = scheduleService.getById(id);
        return Result.success(schedule);
    }
    
    @GetMapping("/teacher/{teacherId}")
    public Result<List<Schedule>> getSchedulesByTeacher(@PathVariable Long teacherId) {
        List<Schedule> schedules = scheduleService.getSchedulesByTeacher(teacherId);
        return Result.success(schedules);
    }
    
    @GetMapping("/course/{courseId}")
    public Result<List<Schedule>> getSchedulesByCourse(@PathVariable Long courseId) {
        List<Schedule> schedules = scheduleService.getSchedulesByCourse(courseId);
        return Result.success(schedules);
    }
    
    @GetMapping("/classroom/{classroomId}")
    public Result<List<Schedule>> getSchedulesByClassroom(@PathVariable Long classroomId) {
        List<Schedule> schedules = scheduleService.getSchedulesByClassroom(classroomId);
        return Result.success(schedules);
    }
    
    @GetMapping("/timeslot")
    public Result<List<Schedule>> getSchedulesByTimeSlot(
            @RequestParam Integer weekDay,
            @RequestParam Integer timeSlot) {
        List<Schedule> schedules = scheduleService.getSchedulesByTimeSlot(weekDay, timeSlot);
        return Result.success(schedules);
    }
    
    @GetMapping("/page")
    public Result<IPage<Schedule>> pageSchedules(
            @RequestParam(defaultValue = "1") Integer current,
            @RequestParam(defaultValue = "10") Integer size) {
        Page<Schedule> page = new Page<>(current, size);
        IPage<Schedule> scheduleIPage = scheduleService.pageSchedules(page);
        return Result.success(scheduleIPage);
    }
    
    @PostMapping
    @RequireRole({User.UserRole.ADMIN})  // 只有管理员可以添加排课
    public Result<Boolean> addSchedule(@RequestBody Schedule schedule) {
        try {
            boolean success = scheduleService.addSchedule(schedule);
            return Result.success(success);
        } catch (RuntimeException e) {
            return Result.error(e.getMessage());
        }
    }
    
    @PutMapping
    @RequireRole({User.UserRole.ADMIN})  // 只有管理员可以修改排课
    public Result<Boolean> updateSchedule(@RequestBody Schedule schedule) {
        try {
            boolean success = scheduleService.updateSchedule(schedule);
            return Result.success(success);
        } catch (RuntimeException e) {
            return Result.error(e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    @RequireRole({User.UserRole.ADMIN})  // 只有管理员可以删除排课
    public Result<Boolean> deleteSchedule(@PathVariable Long id) {
        boolean success = scheduleService.deleteSchedule(id);
        return Result.success(success);
    }
} 