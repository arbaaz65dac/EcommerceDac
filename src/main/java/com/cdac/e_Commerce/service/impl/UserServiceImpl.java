package com.cdac.e_Commerce.service.impl;

import com.cdac.e_Commerce.service.UserService;
import com.cdac.e_Commerce.dto.UserDto;
import com.cdac.e_Commerce.model.User;
import com.cdac.e_Commerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    private UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        return dto;
    }

    private User toEntity(UserDto dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setRole(dto.getRole());
        // Password is not handled in DTO for now
        return user;
    }

    @Override
    public UserDto getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(this::toDto).orElse(null);
    }

    @Override
    public UserDto getUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(this::toDto).orElse(null);
    }

    @Override
    public UserDto registerUser(UserDto userDto) {
        User user = toEntity(userDto);
        // For demo, set password as username (should use encoder and real password in production)
        user.setPassword(userDto.getUsername());
        user.setRole(userDto.getRole() != null ? userDto.getRole() : "USER");
        User saved = userRepository.save(user);
        return toDto(saved);
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        Optional<User> existing = userRepository.findById(id);
        if (existing.isPresent()) {
            User user = existing.get();
            user.setUsername(userDto.getUsername());
            user.setEmail(userDto.getEmail());
            user.setRole(userDto.getRole());
            User saved = userRepository.save(user);
            return toDto(saved);
        }
        return null;
    }

    @Override
    public boolean authenticate(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        return userOpt.map(user -> user.getPassword().equals(password)).orElse(false);
    }
} 