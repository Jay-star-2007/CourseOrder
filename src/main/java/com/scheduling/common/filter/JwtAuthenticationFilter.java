package com.scheduling.common.filter;

import com.scheduling.common.config.JwtConfig;
import com.scheduling.common.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final JwtConfig jwtConfig;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, JwtConfig jwtConfig) {
        this.jwtUtil = jwtUtil;
        this.jwtConfig = jwtConfig;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, 
                                  FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(jwtConfig.getTokenHeader());
        
        if (StringUtils.isNotBlank(token) && token.startsWith(jwtConfig.getTokenPrefix())) {
            token = token.substring(jwtConfig.getTokenPrefix().length());
            
            try {
                if (jwtUtil.validateToken(token)) {
                    Claims claims = jwtUtil.getClaimsFromToken(token);
                    String username = claims.get("username", String.class);
                    String role = claims.get("role", String.class);
                    
                    // 创建认证信息
                    UsernamePasswordAuthenticationToken authentication = 
                        new UsernamePasswordAuthenticationToken(
                            username, 
                            null,
                            Collections.singleton(new SimpleGrantedAuthority("ROLE_" + role))
                        );
                    
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (Exception e) {
                log.error("JWT Authentication failed: {}", e.getMessage());
                SecurityContextHolder.clearContext();
            }
        }
        
        filterChain.doFilter(request, response);
    }
} 