package com.scheduling.common.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtil {
    
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    /**
     * 加密密码
     */
    public static String encode(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }
    
    /**
     * 验证密码
     */
    public static boolean matches(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
    
    /**
     * 生成测试用的加密密码
     */
    public static void main(String[] args) {
        String password = "admin";
        String encodedPassword = encode(password);
        System.out.println("原始密码: " + password);
        System.out.println("加密后: " + encodedPassword);
        System.out.println("验证结果: " + matches(password, encodedPassword));
    }
}