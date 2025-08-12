package com.example.restaurant.service.impl;

import com.example.restaurant.entity.OrderEntity;
import com.example.restaurant.entity.OrderItem;
import com.example.restaurant.exception.OrderNotFoundException;
import com.example.restaurant.repository.OrderItemRepository;
import com.example.restaurant.repository.OrderRepository;
import com.example.restaurant.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    public OrderEntity createOrder(OrderEntity order, List<OrderItem> items) {
        logger.info("Creating order for customer: {}", order.getId());
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus("PLACED");
        var saved = orderRepository.save(order);
        for (var it : items) {
            orderItemRepository.save(it);
        }
        logger.info("Order created with id: {}", saved.getId());
        return saved;
    }

    @Override
    public List<OrderEntity> getAllOrders() {
        logger.info("Fetching all orders");
        return orderRepository.findAll();
    }

    @Override
    public OrderEntity getById(Integer id) {
        logger.info("Fetching order by id: {}", id);
        return orderRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Order not found with id: {}", id);
                    return new OrderNotFoundException("Order not found with id: " + id);
                });
    }

    @Override
    public OrderEntity updateStatus(Integer id, String status) {
        logger.info("Updating status for order id: {} to {}", id, status);
        var ex = getById(id);
        ex.setStatus(status);
        OrderEntity updated = orderRepository.save(ex);
        logger.info("Order status updated for id: {}", updated.getId());
        return updated;
    }

    @Override
    public void deleteOrder(Integer id) {
        logger.info("Deleting order with id: {}", id);
        if (!orderRepository.existsById(id)) {
            logger.warn("Order not found with id: {}", id);
            throw new OrderNotFoundException("Order not found with id: " + id);
        }
        orderRepository.deleteById(id);
        logger.info("Order deleted with id: {}", id);
    }
}