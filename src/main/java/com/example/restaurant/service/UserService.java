package com.example.restaurant.service;

import com.example.restaurant.entity.User;
import java.util.List;

public interface UserService {
    User register(User user);
    String login(String email, String password);
    List<User> getAll();
    User getById(Integer id);
}
