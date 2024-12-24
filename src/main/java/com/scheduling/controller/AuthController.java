package com.scheduling.controller;

import com.scheduling.dto.LoginDTO;
import com.scheduling.service.AuthService;
import com.scheduling.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO loginDTO) {
        LoginVO loginVO = authService.login(loginDTO);
        if (loginVO != null) {
            return ResponseEntity.ok(loginVO);
        }
        return ResponseEntity.badRequest().body("Invalid username or password");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String token) {
        authService.logout(token);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestHeader("Authorization") String token) {
        String newToken = authService.refreshToken(token.substring(7));
        if (newToken != null) {
            return ResponseEntity.ok(newToken);
        }
        return ResponseEntity.badRequest().body("Invalid token");
    }
} 