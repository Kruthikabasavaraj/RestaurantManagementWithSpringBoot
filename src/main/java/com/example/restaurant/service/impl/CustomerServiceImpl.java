package com.example.restaurant.service.impl;

import com.example.restaurant.entity.Customer;
import com.example.restaurant.repository.CustomerRepository;
import com.example.restaurant.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository repo;

    public CustomerServiceImpl(CustomerRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return repo.findAll();
    }

    @Override
    public ResponseEntity<Customer> getCustomer(Integer id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Customer> createCustomer(Customer customer) {
        Customer saved = repo.save(customer);
        return ResponseEntity.ok(saved);
    }

    @Override
    public ResponseEntity<Customer> updateCustomer(Integer id, Customer customer) {
        return repo.findById(id)
                .map(existing -> {
                    customer.setId(existing.getId());
                    return ResponseEntity.ok(repo.save(customer));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Void> deleteCustomer(Integer id) {
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Customer> updatePhone(Integer id, String phone) {
        return repo.findById(id)
                .map(existing -> {
                    existing.setPhone(phone);
                    return ResponseEntity.ok(repo.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}