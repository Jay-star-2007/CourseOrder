package com.scheduling.service.impl;

import com.scheduling.common.util.JwtUtil;
import com.scheduling.dto.LoginDTO;
import com.scheduling.entity.User;
import com.scheduling.service.AuthService;
import com.scheduling.service.UserService;
import com.scheduling.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public LoginVO login(LoginDTO loginDTO) {
        User user = userService.getByUsername(loginDTO.getUsername());
        if (user != null && passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            String token = jwtUtil.generateToken(user);
            LoginVO loginVO = new LoginVO();
            loginVO.setToken(token);
            loginVO.setUser(user);
            return loginVO;
        }
        return null;
    }

    @Override
    public void logout(String token) {
        // 可以实现token黑名单等逻辑
    }

    @Override
    public String refreshToken(String oldToken) {
        return null;
    }

} 