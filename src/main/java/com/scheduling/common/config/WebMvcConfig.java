package com.scheduling.common.config;

import com.scheduling.common.interceptor.JwtInterceptor;
import com.scheduling.common.interceptor.RoleInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    
    @Autowired
    private JwtInterceptor jwtInterceptor;
    
    @Autowired
    private RoleInterceptor roleInterceptor;
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // JWT拦截器
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/auth/login");
        
        // 角色拦截器
        registry.addInterceptor(roleInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/auth/login");
    }
} 