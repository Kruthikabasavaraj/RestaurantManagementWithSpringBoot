package com.example.restaurant.controller;

import com.example.restaurant.dto.OrderCreateDTO;
import com.example.restaurant.entity.OrderEntity;
import com.example.restaurant.entity.OrderItem;
import com.example.restaurant.service.OrderService;
import com.example.restaurant.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService service;
    private final OrderItemRepository orderItemRepository;

    @PostMapping
    public ResponseEntity<OrderEntity> create(@RequestBody OrderCreateDTO dto){
        OrderEntity order = new OrderEntity();
        order.setWaiterName(dto.getWaiterName());
        List<OrderItem> items = dto.getItems().stream().map(i -> {
            var oi = new OrderItem();
            oi.setQuantity(i.getQuantity());
            return oi;
        }).collect(Collectors.toList());
        return ResponseEntity.status(201).body(service.createOrder(order, items));
    }

    @GetMapping
    public ResponseEntity<List<OrderEntity>> all(){ return ResponseEntity.ok(service.getAllOrders()); }

    @GetMapping("/{id}")
    public ResponseEntity<OrderEntity> byId(@PathVariable Integer id){ return ResponseEntity.ok(service.getById(id)); }

    @PutMapping("/{id}/status")
    public ResponseEntity<OrderEntity> status(@PathVariable Integer id, @RequestParam String status){
        return ResponseEntity.ok(service.updateStatus(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){ service.deleteOrder(id); return ResponseEntity.noContent().build(); }
}
