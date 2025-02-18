package com.project.pgfinder.service;

import com.project.pgfinder.dao.BookingRepository;
import com.project.pgfinder.dao.PropertyRepository;
import com.project.pgfinder.dao.UserRepository;
import com.project.pgfinder.entity.Booking;
import com.project.pgfinder.entity.BookingStatus;
import com.project.pgfinder.entity.Property;
import com.project.pgfinder.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public List<Booking> getBookingsByUser(Long userId) {
        return bookingRepository.findByUserId(userId);
    }
    public Booking updateBooking(Long bookingId, Booking updatedBooking) {
        Optional<Booking> booking = bookingRepository.findById(bookingId);
        if (booking.isPresent()) {
            Booking existingBooking = booking.get();
            existingBooking.setCheckInDate(updatedBooking.getCheckInDate());
            existingBooking.setCheckOutDate(updatedBooking.getCheckOutDate());
            return bookingRepository.save(existingBooking);
        }
        return null; // Booking not found
    }

    public Booking createBooking(Long userId, Long propertyId, Booking bookingDetails) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Property> property = propertyRepository.findById(propertyId);

        if (user.isPresent() && property.isPresent()) {
            bookingDetails.setUser(user.get());
            bookingDetails.setProperty(property.get());
            bookingDetails.setStatus(BookingStatus.PENDING); 
            return bookingRepository.save(bookingDetails);
        }
        return null; // Return null if user or property is not found
    }

    public Booking confirmBooking(Long bookingId) {
        Optional<Booking> booking = bookingRepository.findById(bookingId);
        if (booking.isPresent()) {
            booking.get().setStatus(BookingStatus.CONFIRMED); 
            return bookingRepository.save(booking.get());
        }
        return null;
    }

    public void cancelBooking(Long bookingId) {
        Optional<Booking> booking = bookingRepository.findById(bookingId);
        if (booking.isPresent()) {
            booking.get().setStatus(BookingStatus.CANCELLED); 
            bookingRepository.save(booking.get());
        }
    }
}
