package com.example.restaurant.service;
import com.example.restaurant.entity.OrderEntity;
import com.example.restaurant.entity.OrderItem;
import java.util.List;

public interface OrderService {
    OrderEntity createOrder(OrderEntity order, List<OrderItem> items);
    List<OrderEntity> getAllOrders();
    OrderEntity getById(Integer id);
    OrderEntity updateStatus(Integer id, String status);
    void deleteOrder(Integer id);
}
