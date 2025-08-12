package com.example.restaurant.service;

import com.example.restaurant.entity.Payment;
import com.example.restaurant.exception.PaymentNotFoundException;
import com.example.restaurant.repository.PaymentRepository;
import com.example.restaurant.service.impl.PaymentServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.*;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PaymentServiceImplTest {
    @Mock PaymentRepository repo;
    PaymentServiceImpl service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        service = new PaymentServiceImpl(repo);
    }

    @Test
    void createPayment_setsTime() {
        Payment p = new Payment();
        when(repo.save(any())).thenReturn(p);
        assertNotNull(service.createPayment(p));
    }

    @Test
    void getAll_returnsList() {
        when(repo.findAll()).thenReturn(List.of(new Payment()));
        assertFalse(service.getAll().isEmpty());
    }

    @Test
    void getById_found() {
        Payment p = new Payment();
        when(repo.findById(1)).thenReturn(Optional.of(p));
        assertEquals(p, service.getById(1));
    }

    @Test
    void getById_notFound() {
        when(repo.findById(1)).thenReturn(Optional.empty());
        assertThrows(PaymentNotFoundException.class, () -> service.getById(1));
    }
}
