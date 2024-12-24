package com.scheduling.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scheduling.entity.Student;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface StudentMapper extends BaseMapper<Student> {
    Student selectByStudentCode(@Param("studentCode") String studentCode);
    List<Student> selectByClassName(@Param("className") String className);
    List<Student> selectByDepartment(@Param("department") String department);
    List<Student> selectByGrade(@Param("grade") String grade);
} 