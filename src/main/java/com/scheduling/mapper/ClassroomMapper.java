package com.scheduling.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scheduling.entity.Classroom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClassroomMapper extends BaseMapper<Classroom> {
    @Select("SELECT * FROM classrooms WHERE room_number = #{roomNumber}")
    Classroom selectByRoomNumber(String roomNumber);
    
    @Select("SELECT * FROM classrooms WHERE room_type = #{type}")
    List<Classroom> selectByType(Classroom.ClassroomType type);
    
    @Select("SELECT * FROM classrooms WHERE capacity >= #{capacity}")
    List<Classroom> selectByCapacityGreaterEqual(Integer capacity);
}
