package com.example.restaurant.controller;

import com.example.restaurant.entity.MenuItem;
import com.example.restaurant.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService service;

    @PostMapping
    public ResponseEntity<MenuItem> create(@RequestBody MenuItem m){ return ResponseEntity.status(201).body(service.createMenuItem(m)); }

    @GetMapping
    public ResponseEntity<List<MenuItem>> all(){ return ResponseEntity.ok(service.getAll()); }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> byId(@PathVariable Integer id){ return ResponseEntity.ok(service.getById(id)); }

    @PutMapping("/{id}")
    public ResponseEntity<MenuItem> update(@PathVariable Integer id, @RequestBody MenuItem m){ return ResponseEntity.ok(service.update(id, m)); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){ service.delete(id); return ResponseEntity.noContent().build(); }

    @PatchMapping("/{id}/price")
    public ResponseEntity<MenuItem> updatePrice(@PathVariable Integer id, @RequestParam Double price) {
        return ResponseEntity.ok(service.updatePrice(id, price));
    }
}
