package com.scheduling.common.interceptor;

import com.scheduling.common.annotation.RequireRole;
import com.scheduling.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Arrays;

@Component
@Slf4j
public class RoleInterceptor implements HandlerInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        
        RequireRole requireRole = method.getAnnotation(RequireRole.class);
        if (requireRole == null) {
            return true;
        }
        
        // 从request中获取用户信息（已由JWT拦截器设置）
        User currentUser = (User) request.getAttribute("currentUser");
        if (currentUser == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        
        // 检查用户角色是否匹配
        User.UserRole[] allowedRoles = requireRole.value();
        boolean hasPermission = Arrays.asList(allowedRoles).contains(currentUser.getRole());
        
        if (!hasPermission) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return false;
        }
        
        return true;
    }
    
    private User getCurrentUser(HttpServletRequest request) {
        // 从session中获取用户信息
        HttpSession session = request.getSession(false);
        if (session != null) {
            return (User) session.getAttribute("currentUser");
        }
        return null;
    }
} 