package com.project.pgfinder.controller;


import com.project.pgfinder.entity.Booking;
import com.project.pgfinder.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/user/{userId}")
    public List<Booking> getUserBookings(@PathVariable Long userId) {
        return bookingService.getBookingsByUser(userId);
    }

    @PostMapping("/{userId}/{propertyId}")
    public Booking createBooking(@PathVariable Long userId, @PathVariable Long propertyId, @RequestBody Booking booking) {
        return bookingService.createBooking(userId, propertyId, booking);
    }

    @DeleteMapping("/{bookingId}")
    public String cancelBooking(@PathVariable Long bookingId) {
        bookingService.cancelBooking(bookingId);
        return "Booking cancelled successfully!";
    }
}
