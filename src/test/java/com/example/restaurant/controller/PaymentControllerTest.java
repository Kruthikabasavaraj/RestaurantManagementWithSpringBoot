package com.example.restaurant.controller;

import com.example.restaurant.entity.Payment;
import com.example.restaurant.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import java.util.Collections;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PaymentController.class)
public class PaymentControllerTest {
    @Autowired private MockMvc mockMvc;
    @MockBean private PaymentService service;

    @Test
    void testGetAll() throws Exception {
        when(service.getAll()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/payments")).andExpect(status().isOk());
    }

    @Test
    void testGetById() throws Exception {
        when(service.getById(1)).thenReturn(new Payment());
        mockMvc.perform(get("/api/payments/1")).andExpect(status().isOk());
    }

    @Test
    void testCreate() throws Exception {
        when(service.createPayment(Mockito.any())).thenReturn(new Payment());
        mockMvc.perform(post("/api/payments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isCreated());
    }
}
