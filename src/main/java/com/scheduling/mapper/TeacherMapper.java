package com.scheduling.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scheduling.entity.Teacher;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface TeacherMapper extends BaseMapper<Teacher> {
    Teacher selectByTeacherCode(@Param("teacherCode") String teacherCode);
    List<Teacher> selectByDepartment(@Param("department") String department);
} 