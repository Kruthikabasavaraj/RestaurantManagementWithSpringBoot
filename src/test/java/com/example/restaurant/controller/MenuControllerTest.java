package com.example.restaurant.controller;

import com.example.restaurant.entity.MenuItem;
import com.example.restaurant.service.MenuService;
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

@WebMvcTest(MenuController.class)
public class MenuControllerTest {
    @Autowired private MockMvc mockMvc;
    @MockBean private MenuService service;

    @Test
    void testGetAll() throws Exception {
        when(service.getAll()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/menu")).andExpect(status().isOk());
    }

    @Test
    void testGetById() throws Exception {
        when(service.getById(1)).thenReturn(new MenuItem());
        mockMvc.perform(get("/api/menu/1")).andExpect(status().isOk());
    }

    @Test
    void testCreate() throws Exception {
        when(service.createMenuItem(Mockito.any())).thenReturn(new MenuItem());
        mockMvc.perform(post("/api/menu")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Pizza\",\"price\":10.0}"))
                .andExpect(status().isCreated());
    }

    @Test
    void testUpdate() throws Exception {
        when(service.update(Mockito.eq(1), Mockito.any())).thenReturn(new MenuItem());
        mockMvc.perform(put("/api/menu/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Pizza\",\"price\":12.0}"))
                .andExpect(status().isOk());
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/api/menu/1")).andExpect(status().isNoContent());
    }

    @Test
    void testUpdatePrice() throws Exception {
        when(service.updatePrice(1, 15.0)).thenReturn(new MenuItem());
        mockMvc.perform(patch("/api/menu/1/price?price=15.0")).andExpect(status().isOk());
    }
}