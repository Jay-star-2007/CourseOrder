package com.scheduling.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scheduling.entity.Course;
import com.scheduling.mapper.CourseMapper;
import com.scheduling.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
    
    @Autowired
    private CourseMapper courseMapper;
    
    @Override
    public Course getCourseByCourseCode(String courseCode) {
        return courseMapper.selectByCourseCode(courseCode);
    }
    
    @Override
    public List<Course> getCoursesByName(String courseName) {
        return courseMapper.selectByCourseName(courseName);
    }
    
    @Override
    public boolean addCourse(Course course) {
        return save(course);
    }
    
    @Override
    public boolean updateCourse(Course course) {
        return updateById(course);
    }
    
    @Override
    public boolean deleteCourse(Long id) {
        return removeById(id);
    }
    
    @Override
    public IPage<Course> pageCourses(Page<Course> page) {
        return page(page);
    }
} 