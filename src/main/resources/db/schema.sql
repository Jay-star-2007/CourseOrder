-- 创建数据库
CREATE DATABASE IF NOT EXISTS course_scheduling DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

USE course_scheduling;

-- 用户表
CREATE TABLE IF NOT EXISTS users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    username VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名',
    password VARCHAR(100) NOT NULL COMMENT '密码',
    real_name VARCHAR(50) COMMENT '真实姓名',
    role VARCHAR(20) NOT NULL COMMENT '角色：STUDENT/TEACHER/ADMIN',
    enabled BOOLEAN DEFAULT TRUE COMMENT '是否启用',
    last_login_time DATETIME COMMENT '最后登录时间',
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    UNIQUE KEY uk_username (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 课程表
CREATE TABLE IF NOT EXISTS courses (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    course_name VARCHAR(100) NOT NULL COMMENT '课程名称',
    course_code VARCHAR(50) UNIQUE COMMENT '课程代码',
    credit_hours INT NOT NULL COMMENT '学分',
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    UNIQUE KEY uk_course_code (course_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='课程表';

-- 教师表
CREATE TABLE IF NOT EXISTS teachers (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    name VARCHAR(50) NOT NULL COMMENT '教师姓名',
    teacher_code VARCHAR(50) UNIQUE COMMENT '教师编号',
    department VARCHAR(50) COMMENT '所属院系',
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    UNIQUE KEY uk_teacher_code (teacher_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='教师表';

-- 教室表
CREATE TABLE IF NOT EXISTS classrooms (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    room_number VARCHAR(50) NOT NULL UNIQUE COMMENT '教室编号',
    capacity INT NOT NULL COMMENT '容量',
    building VARCHAR(50) COMMENT '所在教学楼',
    room_type VARCHAR(20) NOT NULL COMMENT '教室类型：LECTURE_ROOM/COMPUTER_LAB/LABORATORY',
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    UNIQUE KEY uk_room_number (room_number)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='教室表';

-- 排课表
CREATE TABLE IF NOT EXISTS schedules (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    course_id BIGINT NOT NULL COMMENT '课程ID',
    teacher_id BIGINT NOT NULL COMMENT '教师ID',
    classroom_id BIGINT NOT NULL COMMENT '教室ID',
    week_day INT NOT NULL COMMENT '星期几(1-7)',
    time_slot INT NOT NULL COMMENT '第几节课(1-12)',
    week_number INT NOT NULL COMMENT '第几周',
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    FOREIGN KEY (course_id) REFERENCES courses(id),
    FOREIGN KEY (teacher_id) REFERENCES teachers(id),
    FOREIGN KEY (classroom_id) REFERENCES classrooms(id),
    UNIQUE KEY uk_schedule (week_day, time_slot, week_number, classroom_id),
    UNIQUE KEY uk_schedule_teacher (week_day, time_slot, week_number, teacher_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='排课表';

-- 教师课程关联表
CREATE TABLE IF NOT EXISTS teacher_course (
    teacher_id BIGINT NOT NULL COMMENT '教师ID',
    course_id BIGINT NOT NULL COMMENT '课程ID',
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (teacher_id, course_id),
    FOREIGN KEY (teacher_id) REFERENCES teachers(id),
    FOREIGN KEY (course_id) REFERENCES courses(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='教师课程关联表';

-- 学生表
CREATE TABLE IF NOT EXISTS students (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    student_code VARCHAR(50) NOT NULL UNIQUE COMMENT '学号',
    name VARCHAR(50) NOT NULL COMMENT '姓名',
    gender VARCHAR(10) COMMENT '性别',
    class_name VARCHAR(50) COMMENT '班级',
    department VARCHAR(50) COMMENT '院系',
    grade VARCHAR(20) COMMENT '年级',
    phone VARCHAR(20) COMMENT '联系电话',
    email VARCHAR(100) COMMENT '邮箱',
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    UNIQUE KEY uk_student_code (student_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='学生表';

-- 学生选课表
CREATE TABLE IF NOT EXISTS student_course (
    student_id BIGINT NOT NULL COMMENT '学生ID',
    course_id BIGINT NOT NULL COMMENT '课程ID',
    score DECIMAL(5,2) COMMENT '成绩',
    status VARCHAR(20) DEFAULT 'SELECTED' COMMENT '状态：SELECTED-已选/COMPLETED-已修完',
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (student_id, course_id),
    FOREIGN KEY (student_id) REFERENCES students(id),
    FOREIGN KEY (course_id) REFERENCES courses(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='学生选课表'; 