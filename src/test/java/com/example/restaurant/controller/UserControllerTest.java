package com.example.restaurant.controller;


import com.example.restaurant.entity.User;
import com.example.restaurant.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;

    @Test
    void testGetAll() throws Exception {
        when(userService.getAll()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/users")).andExpect(status().isOk());
    }

    @Test
    void testGetById() throws Exception {
        when(userService.getById(1)).thenReturn(new User());
        mockMvc.perform(get("/api/users/1")).andExpect(status().isOk());
    }
}
