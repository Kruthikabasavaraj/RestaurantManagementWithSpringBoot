package com.example.restaurant.service.impl;

import com.example.restaurant.entity.OrderEntity;
import com.example.restaurant.entity.OrderItem;
import com.example.restaurant.exception.BookingNotFoundException;
import com.example.restaurant.exception.OrderNotFoundException;
import com.example.restaurant.repository.BookingRepository;
import com.example.restaurant.repository.MenuItemRepository;
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
    private final BookingRepository bookingRepository;
    private final MenuItemRepository menuRepository;

    // Java
    @Override
    public OrderEntity createOrder(OrderEntity order, List<OrderItem> items, Integer bookingId) {
        logger.info("Creating order for customer:");
        order.setCreatedAt(LocalDateTime.now());
        var booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found"));
        order.setBooking(booking);
        order.setStatus(OrderEntity.OrderStatus.PLACED);

        for (var it : items) {
            if (it.getMenu() == null && it.getMenuId() != null) {
                var menu = menuRepository.findById(it.getMenuId())
                        .orElseThrow(() -> new RuntimeException("Menu not found"));
                it.setMenu(menu);
            }
            it.setOrder(order); // Link to parent order before saving
        }
        order.setItems(items); // Set items in order

        var saved = orderRepository.save(order); // Cascade saves items
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
        ex.setStatus(OrderEntity.OrderStatus.valueOf(status.toUpperCase()));
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