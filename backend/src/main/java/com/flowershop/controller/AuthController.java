package com.flowershop.controller;

import com.flowershop.model.dto.ApiResponse;
import com.flowershop.model.dto.LoginRequest;
import com.flowershop.model.dto.RegisterRequest;
import com.flowershop.model.entity.User;
import com.flowershop.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ApiResponse<User> register(@Valid @RequestBody RegisterRequest request) {
        return userService.register(request);
    }

    @PostMapping("/login")
    public ApiResponse<User> login(@Valid @RequestBody LoginRequest request) {
        return userService.login(request);
    }

    @GetMapping("/user/{id}")
    public ApiResponse<User> getUser(@PathVariable Long id) {
        return userService.getCurrentUser(id);
    }
}
