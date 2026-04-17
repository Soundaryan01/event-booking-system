package com.eventbooking.eventbookingsystem.controller;

import com.eventbooking.eventbookingsystem.entity.Booking;
import com.eventbooking.eventbookingsystem.service.BookingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/user/{userId}/event/{eventId}")
    public Booking createBooking(@PathVariable Long userId, @PathVariable Long eventId) {
        return bookingService.createBooking(userId, eventId);
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/{bookingId}")
    public Booking getBookingById(@PathVariable Long bookingId) {
        Optional<Booking> booking = bookingService.getBookingById(bookingId);
        return booking.orElse(null);
    }

    @GetMapping("/user/{userId}")
    public List<Booking> getBookingsByUser(@PathVariable Long userId) {
        return bookingService.getBookingsByUser(userId);
    }

    @GetMapping("/event/{eventId}")
    public List<Booking> getBookingsByEvent(@PathVariable Long eventId) {
        return bookingService.getBookingsByEvent(eventId);
    }

    @PutMapping("/{bookingId}/cancel")
    public Booking cancelBooking(@PathVariable Long bookingId) {
        return bookingService.cancelBooking(bookingId);
    }
}