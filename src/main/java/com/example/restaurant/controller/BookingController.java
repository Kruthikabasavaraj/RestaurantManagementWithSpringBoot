package com.example.restaurant.controller;

import com.example.restaurant.entity.Booking;
import com.example.restaurant.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService service;

    @PostMapping
    public ResponseEntity<Booking> create(@RequestBody Booking booking){ return ResponseEntity.status(201).body(service.createBooking(booking)); }

    @GetMapping
    public ResponseEntity<List<Booking>> all(){ return ResponseEntity.ok(service.getAllBookings()); }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> byId(@PathVariable Integer id){ return ResponseEntity.ok(service.getBookingById(id)); }

    @PutMapping("/{id}")
    public ResponseEntity<Booking> update(@PathVariable Integer id, @RequestBody Booking b){ return ResponseEntity.ok(service.updateBooking(id, b)); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){ service.deleteBooking(id); return ResponseEntity.noContent().build(); }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Booking> updateStatus(@PathVariable Integer id, @RequestParam Integer numberOfPeople) {
        return ResponseEntity.ok(service.updateBookingNoOfPeople(id, numberOfPeople));
    }
}
