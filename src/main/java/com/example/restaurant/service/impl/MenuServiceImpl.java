package com.example.restaurant.service.impl;

import com.example.restaurant.entity.MenuItem;
import com.example.restaurant.exception.MenuItemNotFoundException;
import com.example.restaurant.repository.MenuItemRepository;
import com.example.restaurant.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
    private static final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);
    private final MenuItemRepository repo;

    @Override
    public MenuItem createMenuItem(MenuItem item) {
        logger.info("Creating menu item: {}", item.getName());
        item.setCreatedTime(LocalDateTime.now());
        item.setUpdatedTime(LocalDateTime.now());
        MenuItem saved = repo.save(item);
        logger.info("Menu item created with id: {}", saved.getId());
        return saved;
    }

    @Override
    public List<MenuItem> getAll() {
        logger.info("Fetching all menu items");
        return repo.findAll();
    }

    @Override
    public MenuItem getById(Integer id) {
        logger.info("Fetching menu item by id: {}", id);
        return repo.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Menu item not found with id: {}", id);
                    return new MenuItemNotFoundException("Menu item not found with id: " + id);
                });
    }

    @Override
    public MenuItem update(Integer id, MenuItem item) {
        logger.info("Updating menu item with id: {}", id);
        var ex = getById(id);
        ex.setName(item.getName());
        ex.setPrice(item.getPrice());
        ex.setUpdatedTime(LocalDateTime.now());
        MenuItem updated = repo.save(ex);
        logger.info("Menu item updated with id: {}", updated.getId());
        return updated;
    }

    @Override
    public void delete(Integer id) {
        logger.info("Deleting menu item with id: {}", id);
        if (!repo.existsById(id)) {
            logger.warn("Menu item not found with id: {}", id);
            throw new MenuItemNotFoundException("Menu item not found with id: " + id);
        }
        repo.deleteById(id);
        logger.info("Menu item deleted with id: {}", id);
    }

    @Override
    public MenuItem updatePrice(Integer id, Double price) {
        logger.info("Updating price for menu item id: {} to {}", id, price);
        var item = getById(id);
        item.setPrice(price);
        item.setUpdatedTime(LocalDateTime.now());
        MenuItem updated = repo.save(item);
        logger.info("Menu item price updated for id: {}", updated.getId());
        return updated;
    }
}