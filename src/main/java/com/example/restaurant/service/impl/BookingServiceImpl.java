package com.example.restaurant.service.impl;

import com.example.restaurant.entity.Booking;
import com.example.restaurant.exception.BookingNotFoundException;
import com.example.restaurant.repository.BookingRepository;
import com.example.restaurant.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;

    @Override
    public Booking createBooking(Booking booking) {
        booking.setCreatedTime(LocalDateTime.now());
        booking.setUpdatedTime(LocalDateTime.now());
        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking getBookingById(Integer id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found with id: " + id));
    }

    @Override
    public Booking updateBooking(Integer id, Booking booking) {
        var ex = getBookingById(id);
        ex.setBookingTime(booking.getBookingTime());
        ex.setNumPeople(booking.getNumPeople());
        ex.setUpdatedTime(LocalDateTime.now());
        return bookingRepository.save(ex);
    }

    @Override
    public void deleteBooking(Integer id) {
        if (!bookingRepository.existsById(id)) {
            throw new BookingNotFoundException("Booking not found with id: " + id);
        }
        bookingRepository.deleteById(id);
    }

    @Override
    public Booking updateBookingNoOfPeople(Integer id, Integer numPeople) {
        var booking = getBookingById(id);
        booking.setNumPeople(numPeople);
        booking.setUpdatedTime(LocalDateTime.now());
        return bookingRepository.save(booking);
    }
}