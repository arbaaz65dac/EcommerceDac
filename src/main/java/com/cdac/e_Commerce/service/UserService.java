package com.cdac.e_Commerce.service;

import com.cdac.e_Commerce.dto.UserDto;

public interface UserService {
    UserDto getUserById(Long id);
    UserDto getUserByUsername(String username);
    UserDto registerUser(UserDto userDto);
    UserDto updateUser(Long id, UserDto userDto);
    boolean authenticate(String username, String password);
} 