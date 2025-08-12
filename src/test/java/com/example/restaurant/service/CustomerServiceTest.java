package com.example.restaurant.service;

import com.example.restaurant.entity.Customer;
import com.example.restaurant.repository.CustomerRepository;
import com.example.restaurant.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {

    @Test
    public void testFindById() {
        CustomerRepository repo = mock(CustomerRepository.class);
        CustomerService service = new CustomerServiceImpl(repo);
        Customer c = new Customer();
        c.setId(1);
        when(repo.findById(1)).thenReturn(Optional.of(c));
        var res = service.getCustomer(1);
        assertTrue(res.getStatusCode().is2xxSuccessful());
        assertNotNull(res.getBody());
        assertEquals(1, res.getBody().getId());
    }
}
