package com.example.restaurant.controller;

import com.example.restaurant.entity.MenuItem;
import com.example.restaurant.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
public class MenuController {
    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);
    private final MenuService service;

    @PostMapping
    public ResponseEntity<MenuItem> create(@RequestBody MenuItem m) {
        logger.info("Creating menu item: {}", m);
        return ResponseEntity.status(201).body(service.createMenuItem(m));
    }

    @GetMapping
    public ResponseEntity<List<MenuItem>> all() {
        logger.info("Fetching all menu items");
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> byId(@PathVariable Integer id) {
        logger.info("Fetching menu item by id: {}", id);
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuItem> update(@PathVariable Integer id, @RequestBody MenuItem m) {
        logger.info("Updating menu item id: {}", id);
        return ResponseEntity.ok(service.update(id, m));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        logger.info("Deleting menu item id: {}", id);
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/price")
    public ResponseEntity<MenuItem> updatePrice(@PathVariable Integer id, @RequestParam Double price) {
        logger.info("Updating price for menu item id: {} to {}", id, price);
        return ResponseEntity.ok(service.updatePrice(id, price));
    }
}