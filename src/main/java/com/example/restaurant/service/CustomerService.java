package com.example.restaurant.service;

import com.example.restaurant.entity.Customer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();

    ResponseEntity<Customer> getCustomer(Integer id);

    ResponseEntity<Customer> createCustomer(Customer customer);

    ResponseEntity<Customer> updateCustomer(Integer id, Customer customer);

    ResponseEntity<Void> deleteCustomer(Integer id);

    ResponseEntity<Customer> updatePhone(Integer id, String phone);
}