package com.example.restaurant.controller;

import com.example.restaurant.entity.Booking;
import com.example.restaurant.service.BookingService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import java.util.Collections;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
        import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookingController.class)
public class BookingControllerTest {
    @Autowired private MockMvc mockMvc;
    @MockBean private BookingService service;

    @Test
    void testGetAll() throws Exception {
        when(service.getAllBookings()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/bookings")).andExpect(status().isOk());
    }

    @Test
    void testGetById() throws Exception {
        when(service.getBookingById(1)).thenReturn(new Booking());
        mockMvc.perform(get("/api/bookings/1")).andExpect(status().isOk());
    }

    @Test
    void testCreate() throws Exception {
        when(service.createBooking(Mockito.any())).thenReturn(new Booking());
        mockMvc.perform(post("/api/bookings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isCreated());
    }

    @Test
    void testUpdate() throws Exception {
        when(service.updateBooking(Mockito.eq(1), Mockito.any())).thenReturn(new Booking());
        mockMvc.perform(put("/api/bookings/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/api/bookings/1")).andExpect(status().isNoContent());
    }

    @Test
    void testUpdateNoOfPeople() throws Exception {
        when(service.updateBookingNoOfPeople(1, 5)).thenReturn(new Booking());
        mockMvc.perform(patch("/api/bookings/1/status?numberOfPeople=5")).andExpect(status().isOk());
    }
}