package com.example.restaurant.controller;

import com.example.restaurant.entity.Customer;
import com.example.restaurant.service.CustomerService;
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

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
    @Autowired private MockMvc mockMvc;
    @MockBean private CustomerService service;

    @Test
    void testGetAll() throws Exception {
        when(service.getAllCustomers()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/customers")).andExpect(status().isOk());
    }

    @Test
    void testGetById() throws Exception {
        when(service.getCustomer(1)).thenReturn(ResponseEntity.ok(new Customer()));
        mockMvc.perform(get("/api/customers/1")).andExpect(status().isOk());
    }

    @Test
    void testCreate() throws Exception {
        when(service.createCustomer(Mockito.any())).thenReturn(ResponseEntity.ok(new Customer()));
        mockMvc.perform(post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Test\",\"phone\":\"123\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdate() throws Exception {
        when(service.updateCustomer(Mockito.eq(1), Mockito.any())).thenReturn(ResponseEntity.ok(new Customer()));
        mockMvc.perform(put("/api/customers/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Test\",\"phone\":\"123\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testDelete() throws Exception {
        when(service.deleteCustomer(1)).thenReturn(ResponseEntity.noContent().build());
        mockMvc.perform(delete("/api/customers/1")).andExpect(status().isNoContent());
    }

    @Test
    void testUpdatePhone() throws Exception {
        when(service.updatePhone(1, "999")).thenReturn(ResponseEntity.ok(new Customer()));
        mockMvc.perform(patch("/api/customers/1/phone?phone=999")).andExpect(status().isOk());
    }
}
