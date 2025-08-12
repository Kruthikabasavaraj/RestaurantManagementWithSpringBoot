package com.example.restaurant.service.impl;

import com.example.restaurant.entity.Booking;
import com.example.restaurant.exception.BookingNotFoundException;
import com.example.restaurant.repository.BookingRepository;
import com.example.restaurant.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private static final Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);
    private final BookingRepository bookingRepository;

    @Override
    public Booking createBooking(Booking booking) {
        logger.info("Creating booking for customer: {}", booking.getId());
        booking.setCreatedTime(LocalDateTime.now());
        booking.setUpdatedTime(LocalDateTime.now());
        Booking saved = bookingRepository.save(booking);
        logger.info("Booking created with id: {}", saved.getId());
        return saved;
    }

    @Override
    public List<Booking> getAllBookings() {
        logger.info("Fetching all bookings");
        return bookingRepository.findAll();
    }

    @Override
    public Booking getBookingById(Integer id) {
        logger.info("Fetching booking by id: {}", id);
        return bookingRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Booking not found with id: {}", id);
                    return new BookingNotFoundException("Booking not found with id: " + id);
                });
    }

    @Override
    public Booking updateBooking(Integer id, Booking booking) {
        logger.info("Updating booking with id: {}", id);
        var ex = getBookingById(id);
        ex.setBookingTime(booking.getBookingTime());
        ex.setNumPeople(booking.getNumPeople());
        ex.setUpdatedTime(LocalDateTime.now());
        Booking updated = bookingRepository.save(ex);
        logger.info("Booking updated with id: {}", updated.getId());
        return updated;
    }

    @Override
    public void deleteBooking(Integer id) {
        logger.info("Deleting booking with id: {}", id);
        if (!bookingRepository.existsById(id)) {
            logger.warn("Booking not found with id: {}", id);
            throw new BookingNotFoundException("Booking not found with id: " + id);
        }
        bookingRepository.deleteById(id);
        logger.info("Booking deleted with id: {}", id);
    }

    @Override
    public Booking updateBookingNoOfPeople(Integer id, Integer numPeople) {
        logger.info("Updating number of people for booking id: {} to {}", id, numPeople);
        var booking = getBookingById(id);
        booking.setNumPeople(numPeople);
        booking.setUpdatedTime(LocalDateTime.now());
        Booking updated = bookingRepository.save(booking);
        logger.info("Booking number of people updated for id: {}", updated.getId());
        return updated;
    }
}