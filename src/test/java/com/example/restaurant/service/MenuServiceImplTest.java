package com.example.restaurant.service;

import com.example.restaurant.entity.MenuItem;
import com.example.restaurant.exception.MenuItemNotFoundException;
import com.example.restaurant.repository.MenuItemRepository;
import com.example.restaurant.service.impl.MenuServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.*;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MenuServiceImplTest {
    @Mock MenuItemRepository repo;
    MenuServiceImpl service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        service = new MenuServiceImpl(repo);
    }

    @Test
    void createMenuItem_setsTimes() {
        MenuItem item = new MenuItem();
        when(repo.save(any())).thenReturn(item);
        assertNotNull(service.createMenuItem(item));
    }

    @Test
    void getAll_returnsList() {
        when(repo.findAll()).thenReturn(List.of(new MenuItem()));
        assertFalse(service.getAll().isEmpty());
    }

    @Test
    void getById_found() {
        MenuItem item = new MenuItem();
        when(repo.findById(1)).thenReturn(Optional.of(item));
        assertEquals(item, service.getById(1));
    }

    @Test
    void getById_notFound() {
        when(repo.findById(1)).thenReturn(Optional.empty());
        assertThrows(MenuItemNotFoundException.class, () -> service.getById(1));
    }

    @Test
    void update_found() {
        MenuItem item = new MenuItem();
        when(repo.findById(1)).thenReturn(Optional.of(item));
        when(repo.save(any())).thenReturn(item);
        assertEquals(item, service.update(1, item));
    }

    @Test
    void delete_found() {
        when(repo.existsById(1)).thenReturn(true);
        doNothing().when(repo).deleteById(1);
        assertDoesNotThrow(() -> service.delete(1));
    }

    @Test
    void updatePrice_found() {
        MenuItem item = new MenuItem();
        when(repo.findById(1)).thenReturn(Optional.of(item));
        when(repo.save(any())).thenReturn(item);
        assertEquals(item, service.updatePrice(1, 10.0));
    }
}
