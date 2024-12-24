package com.scheduling.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scheduling.entity.Course;

import java.util.List;

/**
 * Description：课程Service层
 * TODO
 *
 * @author Shao
 * @return
 * @date 2024/12/24 21:00
 */
public interface CourseService extends IService<Course> {
    // 根据课程代码查询课程
    Course getCourseByCourseCode(String courseCode);
    
    // 根据课程名称模糊查询
    List<Course> getCoursesByName(String courseName);
    
    // 添加课程
    boolean addCourse(Course course);
    
    // 更新课程信息
    boolean updateCourse(Course course);
    
    // 删除课程
    boolean deleteCourse(Long id);
    
    // 分页查询课程
    IPage<Course> pageCourses(Page<Course> page);
} 