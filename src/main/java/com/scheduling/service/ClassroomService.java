package com.scheduling.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scheduling.entity.Classroom;

import java.util.List;

/**
 * Description：教室Service层
 * TODO
 *
 * @author Shao
 * @return
 * @date 2024/12/24 21:00
 */
public interface ClassroomService extends IService<Classroom> {
    // 根据教室编号查询教室
    Classroom getClassroomByNumber(String roomNumber);
    
    // 根据教室类型查询教室列表
    List<Classroom> getClassroomsByType(Classroom.ClassroomType type);
    
    // 查询大于等于指定容量的教室
    List<Classroom> getClassroomsByCapacity(Integer capacity);
    
    // 添加教室
    boolean addClassroom(Classroom classroom);
    
    // 更新教室信息
    boolean updateClassroom(Classroom classroom);
    
    // 删除教室
    boolean deleteClassroom(Long id);
    
    // 分页查询教室
    IPage<Classroom> pageClassrooms(Page<Classroom> page);
} 