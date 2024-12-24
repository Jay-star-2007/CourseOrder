package com.scheduling.service.impl;

import com.scheduling.entity.Teacher;
import com.scheduling.mapper.TeacherMapper;
import com.scheduling.service.TeacherService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {
    
    @Autowired
    private TeacherMapper teacherMapper;
    
    @Override
    public Teacher getTeacherByCode(String teacherCode) {
        return teacherMapper.selectByTeacherCode(teacherCode);
    }
    
    @Override
    public List<Teacher> getTeachersByDepartment(String department) {
        return teacherMapper.selectByDepartment(department);
    }
    
    @Override
    public boolean addTeacher(Teacher teacher) {
        return save(teacher);
    }
    
    @Override
    public boolean updateTeacher(Teacher teacher) {
        return updateById(teacher);
    }
    
    @Override
    public boolean deleteTeacher(Long id) {
        return removeById(id);
    }
    
    @Override
    public IPage<Teacher> pageTeachers(Page<Teacher> page) {
        return page(page);
    }
} 