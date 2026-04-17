package com.eventbooking.eventbookingsystem.service;

import com.eventbooking.eventbookingsystem.entity.Booking;
import com.eventbooking.eventbookingsystem.entity.Event;
import com.eventbooking.eventbookingsystem.entity.Ticket;
import com.eventbooking.eventbookingsystem.entity.User;
import com.eventbooking.eventbookingsystem.repository.BookingRepository;
import com.eventbooking.eventbookingsystem.repository.EventRepository;
import com.eventbooking.eventbookingsystem.repository.TicketRepository;
import com.eventbooking.eventbookingsystem.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final TicketRepository ticketRepository;

    public BookingService(BookingRepository bookingRepository,
                          UserRepository userRepository,
                          EventRepository eventRepository,
                          TicketRepository ticketRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.ticketRepository = ticketRepository;
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

    List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> getBookingById(Long bookingId) {
        return bookingRepository.findById(bookingId);
    }

    public List<Booking> getBookingsByUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return bookingRepository.findAll()
                .stream()
                .filter(booking -> booking.getUser().equals(user))
                .toList();
    }

    public List<Booking> getBookingsByEvent(Long eventId) {

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        return bookingRepository.findAll()
                .stream()
                .filter(booking -> booking.getEvent().equals(event))
                .toList();
    }

    public void cancelBooking(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setBookingStatus("CANCELLED");

        bookingRepository.save(booking);

        List<Ticket> tickets = ticketRepository.findByBooking(booking);

        for (Ticket ticket : tickets) {
            ticket.setTicketStatus("CANCELLED");
            ticketRepository.save(ticket);
        }
    }
}
