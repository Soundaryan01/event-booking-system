package com.eventbooking.eventbookingsystem.controller;

import com.eventbooking.eventbookingsystem.dto.BookingDTO;
import com.eventbooking.eventbookingsystem.entity.Booking;
import com.eventbooking.eventbookingsystem.mapper.EntityMapper;
import com.eventbooking.eventbookingsystem.service.BookingService;
import com.eventbooking.eventbookingsystem.wrapper.APIResponse;
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
    public APIResponse<BookingDTO> createBooking(@PathVariable Long userId,
                                                 @PathVariable Long eventId) {

        Booking booking = bookingService.createBooking(userId, eventId);

        return new APIResponse<>(
                true,
                "Booking created successfully",
                EntityMapper.toBookingDTO(booking)
        );
    }

    @GetMapping
    public APIResponse<List<BookingDTO>> getAllBookings() {

        List<BookingDTO> bookings = bookingService.getAllBookings()
                .stream()
                .map(EntityMapper::toBookingDTO)
                .toList();

        return new APIResponse<>(
                true,
                "Bookings fetched successfully",
                bookings
        );
    }

    @GetMapping("/{bookingId}")
    public APIResponse<BookingDTO> getBookingById(@PathVariable Long bookingId) {

        Optional<Booking> booking = bookingService.getBookingById(bookingId);
        if(booking.isEmpty()){
            throw new RuntimeException("Booking not found: "+bookingId);
        }

        return new APIResponse<>(
                true,
                "Booking fetched successfully",
                EntityMapper.toBookingDTO(booking.get())
        );
    }

    @GetMapping("/user/{userId}")
    public APIResponse<List<BookingDTO>> getBookingsByUser(@PathVariable Long userId) {

        List<BookingDTO> bookings = bookingService.getBookingsByUser(userId)
                .stream()
                .map(EntityMapper::toBookingDTO)
                .toList();

        return new APIResponse<>(
                true,
                "Bookings fetched by user",
                bookings
        );
    }

    @GetMapping("/event/{eventId}")
    public APIResponse<List<BookingDTO>> getBookingsByEvent(@PathVariable Long eventId) {

        List<BookingDTO> bookings = bookingService.getBookingsByEvent(eventId)
                .stream()
                .map(EntityMapper::toBookingDTO)
                .toList();

        return new APIResponse<>(
                true,
                "Bookings fetched by event",
                bookings
        );
    }

    @PutMapping("/{bookingId}/cancel")
    public APIResponse<BookingDTO> cancelBooking(@PathVariable Long bookingId) {

        Booking booking = bookingService.cancelBooking(bookingId);

        return new APIResponse<>(
                true,
                "Booking cancelled successfully",
                EntityMapper.toBookingDTO(booking)
        );
    }

}