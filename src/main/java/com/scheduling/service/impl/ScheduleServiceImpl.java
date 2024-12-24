package com.scheduling.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scheduling.entity.Schedule;
import com.scheduling.mapper.ScheduleMapper;
import com.scheduling.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ScheduleServiceImpl extends ServiceImpl<ScheduleMapper, Schedule> implements ScheduleService {
    
    @Autowired
    private ScheduleMapper scheduleMapper;
    
    @Override
    public List<Schedule> getSchedulesByTeacher(Long teacherId) {
        return scheduleMapper.selectByTeacherId(teacherId);
    }
    
    @Override
    public List<Schedule> getSchedulesByCourse(Long courseId) {
        return scheduleMapper.selectByCourseId(courseId);
    }
    
    @Override
    public List<Schedule> getSchedulesByClassroom(Long classroomId) {
        return scheduleMapper.selectByClassroomId(classroomId);
    }
    
    @Override
    public List<Schedule> getSchedulesByTimeSlot(Integer weekDay, Integer timeSlot) {
        return scheduleMapper.selectByWeekDayAndTimeSlot(weekDay, timeSlot);
    }
    
    @Override
    public boolean addSchedule(Schedule schedule) {
        if (checkTimeConflict(schedule)) {
            throw new RuntimeException("该时间段已有课程安排");
        }
        return save(schedule);
    }
    
    @Override
    public boolean updateSchedule(Schedule schedule) {
        if (checkTimeConflict(schedule)) {
            throw new RuntimeException("该时间段已有课程安排");
        }
        return updateById(schedule);
    }
    
    @Override
    public boolean deleteSchedule(Long id) {
        return removeById(id);
    }
    
    @Override
    public IPage<Schedule> pageSchedules(Page<Schedule> page) {
        return page(page);
    }
    
    @Override
    public boolean checkTimeConflict(Schedule schedule) {
        LambdaQueryWrapper<Schedule> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Schedule::getWeekDay, schedule.getWeekDay())
              .eq(Schedule::getTimeSlot, schedule.getTimeSlot())
              .eq(Schedule::getWeekNumber, schedule.getWeekNumber())
              .and(w -> w.eq(Schedule::getTeacherId, schedule.getTeacherId())
                        .or()
                        .eq(Schedule::getClassroomId, schedule.getClassroomId()));
        
        if (schedule.getId() != null) {
            wrapper.ne(Schedule::getId, schedule.getId());
        }
        
        return count(wrapper) > 0;
    }
} 