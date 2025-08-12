package com.example.restaurant.service.impl;

import com.example.restaurant.service.PaymentService;
import com.example.restaurant.entity.Payment;
import com.example.restaurant.exception.PaymentNotFoundException;
import com.example.restaurant.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);
    private final PaymentRepository repo;

    @Override
    public Payment createPayment(Payment p) {
        logger.info("Creating payment for order: {}", p.getId());
        p.setPaymentTime(LocalDateTime.now());
        Payment saved = repo.save(p);
        logger.info("Payment created with id: {}", saved.getId());
        return saved;
    }

    @Override
    public List<Payment> getAll() {
        logger.info("Fetching all payments");
        return repo.findAll();
    }

    @Override
    public Payment getById(Integer id) {
        logger.info("Fetching payment by id: {}", id);
        return repo.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Payment not found with id: {}", id);
                    return new PaymentNotFoundException("Payment not found with id: " + id);
                });
    }
}