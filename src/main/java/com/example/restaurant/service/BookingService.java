package com.example.restaurant.service;
import com.example.restaurant.entity.Booking;
import java.util.List;

public interface BookingService {
    Booking createBooking(Booking booking);
    List<Booking> getAllBookings();
    Booking getBookingById(Integer id);
    Booking updateBooking(Integer id, Booking booking);
    void deleteBooking(Integer id);
}
