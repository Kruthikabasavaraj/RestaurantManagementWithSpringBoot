package com.example.restaurant.service;
import com.example.restaurant.entity.Payment;
import java.util.List;
public interface PaymentService {
    Payment createPayment(Payment p);
    List<Payment> getAll();
    Payment getById(Integer id);
}
