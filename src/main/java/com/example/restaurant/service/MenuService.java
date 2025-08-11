package com.example.restaurant.service;
import com.example.restaurant.entity.MenuItem;
import java.util.List;
public interface MenuService {
    MenuItem createMenuItem(MenuItem item);
    List<MenuItem> getAll();
    MenuItem getById(Integer id);
    MenuItem update(Integer id, MenuItem item);
    void delete(Integer id);
}
