package com.example.restaurant.service;

import com.example.restaurant.entity.Customer;
import com.example.restaurant.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {

    @Test
    public void testFindById(){
        CustomerRepository repo = mock(CustomerRepository.class);
        CustomerService service = new CustomerService(repo);
        Customer c = new Customer();
        c.setId(1);
        when(repo.findById(1)).thenReturn(Optional.of(c));
        Optional<Customer> res = service.findById(1);
        assertTrue(res.isPresent());
        assertEquals(1, res.get().getId());
    }
}
