package com.scheduling.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scheduling.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface UserMapper extends BaseMapper<User> {
    // 根据用户名查询用户
    User selectByUsername(@Param("username") String username);
    
    // 更新用户状态
    @Update("UPDATE users SET enabled = #{enabled} WHERE id = #{userId}")
    int updateUserStatus(@Param("userId") Long userId, @Param("enabled") boolean enabled);
    
    // 更新最后登录时间
    @Update("UPDATE users SET last_login_time = NOW() WHERE id = #{userId}")
    int updateLastLoginTime(@Param("userId") Long userId);
} 