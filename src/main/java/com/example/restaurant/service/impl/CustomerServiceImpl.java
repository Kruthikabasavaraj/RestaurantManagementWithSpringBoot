package com.example.restaurant.service.impl;

import com.example.restaurant.entity.Customer;
import com.example.restaurant.exception.CustomerNotFoundException;
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
        Customer customer = repo.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
        return ResponseEntity.ok(customer);
    }

    @Override
    public ResponseEntity<Customer> createCustomer(Customer customer) {
        Customer saved = repo.save(customer);
        return ResponseEntity.ok(saved);
    }

    @Override
    public ResponseEntity<Customer> updateCustomer(Integer id, Customer customer) {
        Customer existing = repo.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
        customer.setId(existing.getId());
        return ResponseEntity.ok(repo.save(customer));
    }

    @Override
    public ResponseEntity<Void> deleteCustomer(Integer id) {
        if (!repo.existsById(id)) {
            throw new CustomerNotFoundException("Customer not found with id: " + id);
        }
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Customer> updatePhone(Integer id, String phone) {
        Customer existing = repo.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
        existing.setPhone(phone);
        return ResponseEntity.ok(repo.save(existing));
    }
}