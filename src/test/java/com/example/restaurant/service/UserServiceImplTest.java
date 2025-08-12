package com.example.restaurant.service;

import com.example.restaurant.entity.User;
import com.example.restaurant.exception.UserNotFoundException;
import com.example.restaurant.repository.UserRepository;
import com.example.restaurant.config.JwtUtil;
import com.example.restaurant.service.impl.UserServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {
    @Mock UserRepository repo;
    @Mock PasswordEncoder encoder;
    @Mock AuthenticationManager authManager;
    @Mock JwtUtil jwtUtil;
    UserServiceImpl service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        service = new UserServiceImpl(repo, encoder, authManager, jwtUtil);
    }

    @Test
    void register_encodesPasswordAndSaves() {
        User u = new User();
        u.setPassword("plain");
        when(encoder.encode(any())).thenReturn("hashed");
        when(repo.save(any())).thenReturn(u);
        assertEquals(u, service.register(u));
    }

    @Test
    void login_success() {
        String email = "a@b.com";
        String password = "pw";
        User u = new User();
        when(repo.findByEmail(email)).thenReturn(Optional.of(u));
        when(jwtUtil.generateToken(email)).thenReturn("token");
        doNothing().when(authManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        assertEquals("token", service.login(email, password));
    }

    @Test
    void login_userNotFound() {
        when(repo.findByEmail(any())).thenReturn(Optional.empty());
        doNothing().when(authManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        assertThrows(UserNotFoundException.class, () -> service.login("x", "y"));
    }

    @Test
    void getAll_returnsList() {
        when(repo.findAll()).thenReturn(List.of(new User()));
        assertFalse(service.getAll().isEmpty());
    }

    @Test
    void getById_found() {
        User u = new User();
        when(repo.findById(1)).thenReturn(Optional.of(u));
        assertEquals(u, service.getById(1));
    }

    @Test
    void getById_notFound() {
        when(repo.findById(1)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> service.getById(1));
    }
}