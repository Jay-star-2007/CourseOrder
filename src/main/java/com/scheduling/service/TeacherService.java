package com.scheduling.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scheduling.entity.Teacher;

import java.util.List;

/**
 * Description：教师service层实现
 * TODO
 *
 * @author Shao
 * @return
 * @date 2024/12/24 20:59
 */
public interface TeacherService extends IService<Teacher> {
    // 根据教师编号查询教师
    Teacher getTeacherByCode(String teacherCode);
    
    // 根据部门查询教师列表
    List<Teacher> getTeachersByDepartment(String department);
    
    // 添加教师
    boolean addTeacher(Teacher teacher);
    
    // 更新教师信息
    boolean updateTeacher(Teacher teacher);
    
    // 删除教师
    boolean deleteTeacher(Long id);
    
    // 分页查询教师
    IPage<Teacher> pageTeachers(Page<Teacher> page);
} 