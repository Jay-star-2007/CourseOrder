package com.scheduling.service;

import com.scheduling.dto.LoginDTO;
import com.scheduling.vo.LoginVO;

/**
 * Description：身份登录Service层
 * TODO
 *
 * @author Shao
 * @return
 * @date 2024/12/24 21:01
 */
public interface AuthService {
    LoginVO login(LoginDTO loginDTO);
    void logout(String token);
    String refreshToken(String oldToken);
} 