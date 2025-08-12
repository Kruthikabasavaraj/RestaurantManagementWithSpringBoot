package com.example.restaurant.service.impl;

import com.example.restaurant.entity.OrderEntity;
import com.example.restaurant.entity.OrderItem;
import com.example.restaurant.exception.OrderNotFoundException;
import com.example.restaurant.repository.OrderItemRepository;
import com.example.restaurant.repository.OrderRepository;
import com.example.restaurant.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    public OrderEntity createOrder(OrderEntity order, List<OrderItem> items) {
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus("PLACED");
        var saved = orderRepository.save(order);
        for (var it : items) {
            orderItemRepository.save(it);
        }
        return saved;
    }

    @Override
    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public OrderEntity getById(Integer id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + id));
    }

    @Override
    public OrderEntity updateStatus(Integer id, String status) {
        var ex = getById(id);
        ex.setStatus(status);
        return orderRepository.save(ex);
    }

    @Override
    public void deleteOrder(Integer id) {
        if (!orderRepository.existsById(id)) {
            throw new OrderNotFoundException("Order not found with id: " + id);
        }
        orderRepository.deleteById(id);
    }
}