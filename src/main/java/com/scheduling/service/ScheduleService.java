package com.scheduling.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scheduling.entity.Schedule;

import java.util.List;

/**
 * Description：课表Service层
 * TODO
 *
 * @author Shao
 * @return
 * @date 2024/12/24 20:59
 */
public interface ScheduleService extends IService<Schedule> {
    // 根据教师ID查询课表
    List<Schedule> getSchedulesByTeacher(Long teacherId);
    
    // 根据课程ID查询课表
    List<Schedule> getSchedulesByCourse(Long courseId);
    
    // 根据教室ID查询课表
    List<Schedule> getSchedulesByClassroom(Long classroomId);
    
    // 根据星期和节次查询课表
    List<Schedule> getSchedulesByTimeSlot(Integer weekDay, Integer timeSlot);
    
    // 添加排课记录
    boolean addSchedule(Schedule schedule);
    
    // 更新排课记录
    boolean updateSchedule(Schedule schedule);
    
    // 删除排课记录
    boolean deleteSchedule(Long id);
    
    // 分页查询排课记录
    IPage<Schedule> pageSchedules(Page<Schedule> page);
    
    // 检查时间冲突
    boolean checkTimeConflict(Schedule schedule);
} 