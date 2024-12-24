package com.scheduling.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scheduling.entity.Schedule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ScheduleMapper extends BaseMapper<Schedule> {

    @Select("SELECT * FROM schedules WHERE teacher_id = #{teacherId}")
    List<Schedule> selectByTeacherId(Long teacherId);

    @Select("SELECT * FROM schedules WHERE course_id = #{courseId}")
    List<Schedule> selectByCourseId(Long courseId);

    @Select("SELECT * FROM schedules WHERE classroom_id = #{classroomId}")
    List<Schedule> selectByClassroomId(Long classroomId);

    @Select("SELECT * FROM schedules WHERE week_day = #{weekDay} AND time_slot = #{timeSlot}")
    List<Schedule> selectByWeekDayAndTimeSlot(Integer weekDay, Integer timeSlot);
} 