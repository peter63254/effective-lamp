package com.flowershop.service;

import com.flowershop.model.dto.ApiResponse;
import com.flowershop.model.dto.LoginRequest;
import com.flowershop.model.dto.RegisterRequest;
import com.flowershop.model.entity.User;
import com.flowershop.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ApiResponse<User> register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            return ApiResponse.error("Username already exists");
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword()); // In production, use BCrypt
        user.setPhone(request.getPhone());
        user.setEmail(request.getEmail());
        user.setRole("USER");
        User saved = userRepository.save(user);
        saved.setPassword(null);
        return ApiResponse.success("Registration successful", saved);
    }

    public ApiResponse<User> login(LoginRequest request) {
        Optional<User> userOpt = userRepository.findByUsername(request.getUsername());
        if (userOpt.isEmpty()) {
            return ApiResponse.error("Invalid username or password");
        }
        User user = userOpt.get();
        if (!user.getPassword().equals(request.getPassword())) {
            return ApiResponse.error("Invalid username or password");
        }
        user.setPassword(null);
        return ApiResponse.success("Login successful", user);
    }

    public ApiResponse<User> getCurrentUser(Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            return ApiResponse.error("User not found");
        }
        User user = userOpt.get();
        user.setPassword(null);
        return ApiResponse.success("OK", user);
    }
}
