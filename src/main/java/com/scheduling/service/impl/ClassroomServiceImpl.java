package com.scheduling.service.impl;

import com.scheduling.entity.Classroom;
import com.scheduling.entity.Classroom.ClassroomType;
import com.scheduling.mapper.ClassroomMapper;
import com.scheduling.service.ClassroomService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClassroomServiceImpl extends ServiceImpl<ClassroomMapper, Classroom> implements ClassroomService {
    
    @Autowired
    private ClassroomMapper classroomMapper;
    
    @Override
    public Classroom getClassroomByNumber(String roomNumber) {
        return classroomMapper.selectByRoomNumber(roomNumber);
    }
    
    @Override
    public List<Classroom> getClassroomsByType(ClassroomType type) {
        return classroomMapper.selectByType(type);
    }
    
    @Override
    public List<Classroom> getClassroomsByCapacity(Integer capacity) {
        return classroomMapper.selectByCapacityGreaterEqual(capacity);
    }
    
    @Override
    public boolean addClassroom(Classroom classroom) {
        return save(classroom);
    }
    
    @Override
    public boolean updateClassroom(Classroom classroom) {
        return updateById(classroom);
    }
    
    @Override
    public boolean deleteClassroom(Long id) {
        return removeById(id);
    }
    
    @Override
    public IPage<Classroom> pageClassrooms(Page<Classroom> page) {
        return page(page);
    }
} 