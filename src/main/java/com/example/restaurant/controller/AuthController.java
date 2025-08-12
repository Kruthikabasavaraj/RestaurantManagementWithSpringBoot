package com.example.restaurant.controller;

import com.example.restaurant.dto.*;
import com.example.restaurant.entity.User;
import com.example.restaurant.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User u) {
        logger.info("Registering user: {}", u.getEmail());
        return ResponseEntity.ok(userService.register(u));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest req) {
        logger.info("User login attempt: {}", req.getEmail());
        var token = userService.login(req.getEmail(), req.getPassword());
        return ResponseEntity.ok(new LoginResponse(token));
    }
}