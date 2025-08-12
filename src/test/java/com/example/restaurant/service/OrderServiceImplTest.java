package com.example.restaurant.service;


import com.example.restaurant.entity.OrderEntity;
import com.example.restaurant.entity.OrderItem;
import com.example.restaurant.exception.OrderNotFoundException;
import com.example.restaurant.repository.OrderItemRepository;
import com.example.restaurant.repository.OrderRepository;
import com.example.restaurant.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.*;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceImplTest {
    @Mock OrderRepository orderRepo;
    @Mock OrderItemRepository itemRepo;
    OrderServiceImpl service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        service = new OrderServiceImpl(orderRepo, itemRepo);
    }

    @Test
    void createOrder_savesOrderAndItems() {
        OrderEntity order = new OrderEntity();
        OrderItem item = new OrderItem();
        when(orderRepo.save(any())).thenReturn(order);
        when(itemRepo.save(any())).thenReturn(item);
        assertEquals(order, service.createOrder(order, List.of(item)));
    }

    @Test
    void getAllOrders_returnsList() {
        when(orderRepo.findAll()).thenReturn(List.of(new OrderEntity()));
        assertFalse(service.getAllOrders().isEmpty());
    }

    @Test
    void getById_found() {
        OrderEntity order = new OrderEntity();
        when(orderRepo.findById(1)).thenReturn(Optional.of(order));
        assertEquals(order, service.getById(1));
    }

    @Test
    void getById_notFound() {
        when(orderRepo.findById(1)).thenReturn(Optional.empty());
        assertThrows(OrderNotFoundException.class, () -> service.getById(1));
    }

    @Test
    void updateStatus_found() {
        OrderEntity order = new OrderEntity();
        when(orderRepo.findById(1)).thenReturn(Optional.of(order));
        when(orderRepo.save(any())).thenReturn(order);
        assertEquals(order, service.updateStatus(1, "COMPLETED"));
    }

    @Test
    void deleteOrder_found() {
        when(orderRepo.existsById(1)).thenReturn(true);
        doNothing().when(orderRepo).deleteById(1);
        assertDoesNotThrow(() -> service.deleteOrder(1));
    }

    @Test
    void deleteOrder_notFound() {
        when(orderRepo.existsById(1)).thenReturn(false);
        assertThrows(OrderNotFoundException.class, () -> service.deleteOrder(1));
    }
}
