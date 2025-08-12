package com.example.restaurant.controller;

import com.example.restaurant.entity.Customer;
import com.example.restaurant.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        logger.info("Fetching all customers");
        return service.getAllCustomers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Integer id) {
        logger.info("Fetching customer by id: {}", id);
        return service.getCustomer(id);
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        logger.info("Creating customer: {}", customer);
        return service.createCustomer(customer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Integer id, @RequestBody Customer customer) {
        logger.info("Updating customer id: {}", id);
        return service.updateCustomer(id, customer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Integer id) {
        logger.info("Deleting customer id: {}", id);
        return service.deleteCustomer(id);
    }

    @PatchMapping("/{id}/phone")
    public ResponseEntity<Customer> updatePhone(@PathVariable Integer id, @RequestParam String phone) {
        logger.info("Updating phone for customer id: {} to {}", id, phone);
        return service.updatePhone(id, phone);
    }
}