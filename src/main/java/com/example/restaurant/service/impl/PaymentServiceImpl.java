package com.example.restaurant.service.impl;

import com.example.restaurant.service.PaymentService;
import com.example.restaurant.entity.Payment;
import com.example.restaurant.exception.PaymentNotFoundException;
import com.example.restaurant.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository repo;

    @Override
    public Payment createPayment(Payment p) {
        p.setPaymentTime(LocalDateTime.now());
        return repo.save(p);
    }

    @Override
    public List<Payment> getAll() {
        return repo.findAll();
    }

    @Override
    public Payment getById(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found with id: " + id));
    }
}