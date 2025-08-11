package com.example.restaurant.service;

import com.example.restaurant.entity.Customer;
import com.example.restaurant.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository repo;

    public CustomerService(CustomerRepository repo) {
        this.repo = repo;
    }

    public List<Customer> findAll(){ return repo.findAll(); }
    public Optional<Customer> findById(Integer id){ return repo.findById(id); }
    public Customer save(Customer c){ return repo.save(c); }
    public void deleteById(Integer id){ repo.deleteById(id); }
}
