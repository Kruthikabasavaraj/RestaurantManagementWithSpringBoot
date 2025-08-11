package com.example.restaurant.controller;

import com.example.restaurant.entity.Payment;
import com.example.restaurant.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService service;

    @PostMapping
    public ResponseEntity<Payment> create(@RequestBody Payment p){ return ResponseEntity.status(201).body(service.createPayment(p)); }

    @GetMapping
    public ResponseEntity<List<Payment>> all(){ return ResponseEntity.ok(service.getAll()); }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> byId(@PathVariable Integer id){ return ResponseEntity.ok(service.getById(id)); }
}
