package com.scheduling.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scheduling.entity.User;

/**
 * Description：用户Service功能的实现
 * TODO：下面还要加点其他的东西
 *
 * @author Shao
 * @return
 * @date 2024/12/24 20:58
 */
public interface UserService extends IService<User> {
    // 根据用户名查询用户
    User getByUsername(String username);
    
    // 添加用户
    boolean addUser(User user);
    
    // 更新用户信息
    boolean updateUser(User user);
    
    // 删除用户
    boolean deleteUser(Long id);
    
    // 修改密码
    boolean updatePassword(Long userId, String oldPassword, String newPassword);
    
    // 分页查询用户
    IPage<User> pageUsers(Page<User> page);
    
    // 启用/禁用用户
    boolean updateUserStatus(Long userId, boolean enabled);
    
    // 更新最后登录时间
    boolean updateLastLoginTime(Long userId);
} 