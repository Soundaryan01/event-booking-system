package com.eventbooking.eventbookingsystem.service;

import com.eventbooking.eventbookingsystem.entity.Booking;
import com.eventbooking.eventbookingsystem.entity.Event;
import com.eventbooking.eventbookingsystem.entity.User;
import com.eventbooking.eventbookingsystem.repository.BookingRepository;
import com.eventbooking.eventbookingsystem.repository.EventRepository;
import com.eventbooking.eventbookingsystem.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public BookingService(BookingRepository bookingRepository,
                          UserRepository userRepository,
                          EventRepository eventRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    public Booking createBooking(Long userId, Long eventId) {

        Optional<User> user = userRepository.findById(userId);
        Optional<Event> event = eventRepository.findById(eventId);

        if (user.isEmpty() || event.isEmpty()) {
            throw new RuntimeException("User or Event not found");
        }

        Booking booking = Booking.builder()
                .user(user.get())
                .event(event.get())
                .bookingStatus("CONFIRMED")
                .bookingTime(LocalDateTime.now())
                .build();

        return bookingRepository.save(booking);
    }
}
