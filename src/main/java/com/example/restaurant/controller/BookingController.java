package com.example.restaurant.controller;

import com.example.restaurant.entity.Booking;
import com.example.restaurant.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {
    private static final Logger logger = LoggerFactory.getLogger(BookingController.class);
    private final BookingService service;

    @PostMapping
    public ResponseEntity<Booking> create(@RequestBody Booking booking) {
        logger.info("Creating booking: {}", booking);
        return ResponseEntity.status(201).body(service.createBooking(booking));
    }

    @GetMapping
    public ResponseEntity<List<Booking>> all() {
        logger.info("Fetching all bookings");
        return ResponseEntity.ok(service.getAllBookings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> byId(@PathVariable Integer id) {
        logger.info("Fetching booking by id: {}", id);
        return ResponseEntity.ok(service.getBookingById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Booking> update(@PathVariable Integer id, @RequestBody Booking b) {
        logger.info("Updating booking id: {}", id);
        return ResponseEntity.ok(service.updateBooking(id, b));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        logger.info("Deleting booking id: {}", id);
        service.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Booking> updateStatus(@PathVariable Integer id, @RequestParam Integer numberOfPeople) {
        logger.info("Updating number of people for booking id: {} to {}", id, numberOfPeople);
        return ResponseEntity.ok(service.updateBookingNoOfPeople(id, numberOfPeople));
    }
}