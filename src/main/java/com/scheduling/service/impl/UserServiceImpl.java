package com.scheduling.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scheduling.entity.User;
import com.scheduling.mapper.UserMapper;
import com.scheduling.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public User getByUsername(String username) {
        return userMapper.selectByUsername(username);
    }
    
    @Override
    public boolean addUser(User user) {
        // 加密密码
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return save(user);
    }
    
    @Override
    public boolean updateUser(User user) {
        User existingUser = getById(user.getId());
        if (existingUser == null) {
            return false;
        }
        
        // 如果密码没有改变，保持原密码
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            user.setPassword(existingUser.getPassword());
        } else {
            // 如果密码改变了，需要加密新密码
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        
        return updateById(user);
    }
    
    @Override
    public boolean deleteUser(Long id) {
        return removeById(id);
    }
    
    @Override
    public boolean updatePassword(Long userId, String oldPassword, String newPassword) {
        User user = getById(userId);
        if (user == null) {
            return false;
        }
        
        // 验证旧密码
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            return false;
        }
        
        // 更新新密码
        user.setPassword(passwordEncoder.encode(newPassword));
        return updateById(user);
    }
    
    @Override
    public IPage<User> pageUsers(Page<User> page) {
        return page(page);
    }
    
    @Override
    public boolean updateUserStatus(Long userId, boolean enabled) {
        return userMapper.updateUserStatus(userId, enabled) > 0;
    }
    
    @Override
    public boolean updateLastLoginTime(Long userId) {
        return userMapper.updateLastLoginTime(userId) > 0;
    }
} 