package com.scheduling.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scheduling.entity.Course;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface CourseMapper extends BaseMapper<Course> {
    Course selectByCourseCode(@Param("courseCode") String courseCode);
    List<Course> selectByCourseName(@Param("courseName") String courseName);
} 