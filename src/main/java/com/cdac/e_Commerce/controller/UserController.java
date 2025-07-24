package com.cdac.e_Commerce.controller;

import com.cdac.e_Commerce.dto.UserDto;
import com.cdac.e_Commerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserDto> getCurrentUser(@RequestParam Long userId) {
        UserDto user = userService.getUserById(userId);
        if (user == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user);
    }

    @PutMapping("/update")
    public ResponseEntity<UserDto> updateUser(@RequestParam Long userId, @RequestBody UserDto userUpdateDto) {
        UserDto updated = userService.updateUser(userId, userUpdateDto);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }
} 