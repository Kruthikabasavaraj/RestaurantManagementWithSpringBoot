package com.example.restaurant.service.impl;

import com.example.restaurant.service.MenuService;
import com.example.restaurant.entity.MenuItem;
import com.example.restaurant.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
    private final MenuItemRepository repo;

    @Override
    public MenuItem createMenuItem(MenuItem item) {
        item.setCreatedTime(LocalDateTime.now());
        item.setUpdatedTime(LocalDateTime.now());
        return repo.save(item);
    }

    @Override public List<MenuItem> getAll(){ return repo.findAll(); }
    @Override public MenuItem getById(Integer id){ return repo.findById(id).orElseThrow(); }
    @Override public MenuItem update(Integer id, MenuItem item){
        var ex = getById(id);
        ex.setName(item.getName());
        ex.setPrice(item.getPrice());
        ex.setUpdatedTime(LocalDateTime.now());
        return repo.save(ex);
    }
    @Override public void delete(Integer id){ repo.deleteById(id); }

    @Override
    public MenuItem updatePrice(Integer id, Double price) {
        var item = getById(id);
        item.setPrice(price);
        item.setUpdatedTime(LocalDateTime.now());
        return repo.save(item);
    }
}
