package com.example.restaurant.service.impl;

import com.example.restaurant.entity.Customer;
import com.example.restaurant.exception.CustomerNotFoundException;
import com.example.restaurant.repository.CustomerRepository;
import com.example.restaurant.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
    private final CustomerRepository repo;

    public CustomerServiceImpl(CustomerRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Customer> getAllCustomers() {
        logger.info("Fetching all customers");
        return repo.findAll();
    }

    @Override
    public ResponseEntity<Customer> getCustomer(Integer id) {
        logger.info("Fetching customer by id: {}", id);
        Customer customer = repo.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Customer not found with id: {}", id);
                    return new CustomerNotFoundException("Customer not found with id: " + id);
                });
        return ResponseEntity.ok(customer);
    }

    @Override
    public ResponseEntity<Customer> createCustomer(Customer customer) {
        logger.info("Creating customer: {}", customer.getName());
        Customer saved = repo.save(customer);
        logger.info("Customer created with id: {}", saved.getId());
        return ResponseEntity.ok(saved);
    }

    @Override
    public ResponseEntity<Customer> updateCustomer(Integer id, Customer customer) {
        logger.info("Updating customer with id: {}", id);
        Customer existing = repo.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Customer not found with id: {}", id);
                    return new CustomerNotFoundException("Customer not found with id: " + id);
                });
        customer.setId(existing.getId());
        Customer updated = repo.save(customer);
        logger.info("Customer updated with id: {}", updated.getId());
        return ResponseEntity.ok(updated);
    }

    @Override
    public ResponseEntity<Void> deleteCustomer(Integer id) {
        logger.info("Deleting customer with id: {}", id);
        if (!repo.existsById(id)) {
            logger.warn("Customer not found with id: {}", id);
            throw new CustomerNotFoundException("Customer not found with id: " + id);
        }
        repo.deleteById(id);
        logger.info("Customer deleted with id: {}", id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Customer> updatePhone(Integer id, String phone) {
        logger.info("Updating phone for customer id: {} to {}", id, phone);
        Customer existing = repo.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Customer not found with id: {}", id);
                    return new CustomerNotFoundException("Customer not found with id: " + id);
                });
        existing.setPhone(phone);
        Customer updated = repo.save(existing);
        logger.info("Customer phone updated for id: {}", updated.getId());
        return ResponseEntity.ok(updated);
    }
}