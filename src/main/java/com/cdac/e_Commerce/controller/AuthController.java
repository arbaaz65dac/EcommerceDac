package com.cdac.e_Commerce.controller;

import com.cdac.e_Commerce.dto.UserDto;
import com.cdac.e_Commerce.service.UserService;
import com.cdac.e_Commerce.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody UserDto signupDto) {
        UserDto user = userService.registerUser(signupDto);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginDto) {
        String username = loginDto.get("username");
        String password = loginDto.get("password");
        boolean authenticated = userService.authenticate(username, password);
        if (!authenticated) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Invalid username or password");
            return ResponseEntity.status(401).body(error);
        }
        UserDto user = userService.getUserByUsername(username);
        String token = jwtUtil.generateToken(username, user.getRole());
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("user", user);
        return ResponseEntity.ok(response);
    }
} 