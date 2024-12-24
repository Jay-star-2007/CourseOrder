package com.scheduling.vo;
import com.scheduling.entity.User;
import lombok.Data;
@Data
public class LoginVO {
    private String token;
    private User user;
} 