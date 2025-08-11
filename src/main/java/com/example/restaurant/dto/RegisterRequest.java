package com.example.restaurant.dto;
import com.example.restaurant.entity.User;
import lombok.Data;
@Data
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private User.Role role;
}
