package com.example.restaurant.controller;

import com.example.restaurant.entity.Customer;
import com.example.restaurant.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService service;

    public CustomerController(CustomerService service){ this.service = service; }

    @GetMapping
    public List<Customer> all(){ return service.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> get(@PathVariable Integer id){
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Customer> create(@RequestBody Customer c){
        Customer saved = service.save(c);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> update(@PathVariable Integer id, @RequestBody Customer c){
        return service.findById(id).map(existing -> {
            c.setId(existing.getId());
            return ResponseEntity.ok(service.save(c));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
