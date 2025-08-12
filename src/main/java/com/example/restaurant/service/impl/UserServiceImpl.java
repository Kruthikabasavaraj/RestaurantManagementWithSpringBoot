package com.example.restaurant.service.impl;

import com.example.restaurant.service.UserService;
import com.example.restaurant.entity.User;
import com.example.restaurant.exception.UserNotFoundException;
import com.example.restaurant.repository.UserRepository;
import com.example.restaurant.config.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    public User register(User user) {
        logger.info("Registering new user with email: {}", user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        logger.info("User registered successfully with id: {}", savedUser.getId());
        return savedUser;
    }

    @Override
    public String login(String email, String password) {
        logger.info("Attempting login for email: {}", email);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        var u = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    logger.warn("User not found with email: {}", email);
                    return new UserNotFoundException("User not found with email: " + email);
                });
        String token = jwtUtil.generateToken(u.getEmail());
        logger.info("Login successful for email: {}", email);
        return token;
    }

    @Override
    public List<User> getAll() {
        logger.info("Fetching all users");
        return userRepository.findAll();
    }

    @Override
    public User getById(Integer id) {
        logger.info("Fetching user by id: {}", id);
        return userRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("User not found with id: {}", id);
                    return new UserNotFoundException("User not found with id: " + id);
                });
    }
}