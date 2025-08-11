package com.example.restaurant.controller;

import com.example.restaurant.entity.User;
import com.example.restaurant.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> all(){ return ResponseEntity.ok(userService.getAll()); }

    @GetMapping("/{id}")
    public ResponseEntity<User> byId(@PathVariable Integer id){ return ResponseEntity.ok(userService.getById(id)); }
}
