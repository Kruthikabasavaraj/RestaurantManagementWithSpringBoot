package com.example.restaurant.controller;

import com.example.restaurant.entity.OrderEntity;
import com.example.restaurant.service.OrderService;
import com.example.restaurant.repository.OrderItemRepository;
import com.example.restaurant.config.JwtUtil;
import com.example.restaurant.config.JwtAuthFilter;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
@AutoConfigureMockMvc(addFilters = false)
public class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private OrderService service;
    @MockBean
    private OrderItemRepository orderItemRepository;
    @MockBean
    private JwtUtil jwtUtil; // Add if needed
    @MockBean
    private JwtAuthFilter jwtAuthFilter; // Add if needed

    @Test
    void testGetAll() throws Exception {
        when(service.getAllOrders()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/orders")).andExpect(status().isOk());
    }

    @Test
    void testGetById() throws Exception {
        when(service.getById(1)).thenReturn(new OrderEntity());
        mockMvc.perform(get("/api/orders/1")).andExpect(status().isOk());
    }

    @Test
    void testCreate() throws Exception {
        when(service.createOrder(Mockito.any(), Mockito.anyList(), Mockito.any()))
                .thenReturn(new OrderEntity());
        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"bookingId\":1,\"waiterName\":\"Alice\",\"items\":[{\"menuId\":1,\"quantity\":2}]}"))
                .andExpect(status().isCreated());
    }

    @Test
    void testUpdateStatus() throws Exception {
        when(service.updateStatus(1, "COMPLETED")).thenReturn(new OrderEntity());
        mockMvc.perform(put("/api/orders/1/status?status=COMPLETED")).andExpect(status().isOk());
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/api/orders/1")).andExpect(status().isNoContent());
    }
}