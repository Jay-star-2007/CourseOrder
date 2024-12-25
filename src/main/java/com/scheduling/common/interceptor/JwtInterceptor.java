package com.scheduling.common.interceptor;

import com.scheduling.common.util.JwtUtil;
import com.scheduling.entity.User;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 如果是OPTIONS请求，直接放行
        if (request.getMethod().equals("OPTIONS")) {
            return true;
        }
        
        String token = request.getHeader("Authorization");
        
        // 检查token是否存在
        if (StringUtils.isBlank(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        
        // 验证token
        if (!jwtUtil.validateToken(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        
        // 获取token中的用户信息
        Claims claims = jwtUtil.getClaimsFromToken(token);
        if (claims == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        
        // 将用户信息存入request
        User user = new User();
        user.setUsername(claims.get("username").toString());
        user.setRole(User.UserRole.valueOf(claims.get("role").toString()));
        request.setAttribute("currentUser", user);
        
        return true;
    }
} 