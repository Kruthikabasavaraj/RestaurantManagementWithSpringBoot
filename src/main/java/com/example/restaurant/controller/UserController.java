package com.example.restaurant.controller;

import com.example.restaurant.entity.User;
import com.example.restaurant.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> all() {
        logger.info("Fetching all users");
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> byId(@PathVariable Integer id) {
        logger.info("Fetching user by id: {}", id);
        return ResponseEntity.ok(userService.getById(id));
    }
}