package com.example.restaurant.service;

import com.example.restaurant.entity.Booking;
import com.example.restaurant.repository.BookingRepository;
import com.example.restaurant.service.impl.BookingServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.*;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class BookingServiceTest {
    @Mock BookingRepository repo;
    BookingServiceImpl service;

    @BeforeEach
    void setup(){ MockitoAnnotations.openMocks(this); service = new BookingServiceImpl(repo); }

    @Test
    void createBooking_shouldSetTimes() {
        Booking b = new Booking(
                null,
                null,
                LocalDateTime.now().plusDays(1),
                4,
                null,
                null,
                null
        );        when(repo.save(any())).thenAnswer(i -> {
            Booking arg = i.getArgument(0);
            arg.setId(1);
            return arg;
        });
        var saved = service.createBooking(b);
        assertNotNull(saved.getCreatedTime());
        assertNotNull(saved.getUpdatedTime());
    }

    @Test
    void getAllBookings_returnsList() {
        when(repo.findAll()).thenReturn(List.of(new Booking()));
        var list = service.getAllBookings();
        assertFalse(list.isEmpty());
    }
}
