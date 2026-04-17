package com.eventbooking.eventbookingsystem.service;

import com.eventbooking.eventbookingsystem.entity.Booking;
import com.eventbooking.eventbookingsystem.entity.Event;
import com.eventbooking.eventbookingsystem.entity.Ticket;
import com.eventbooking.eventbookingsystem.entity.Venue;
import com.eventbooking.eventbookingsystem.repository.BookingRepository;
import com.eventbooking.eventbookingsystem.repository.EventRepository;
import com.eventbooking.eventbookingsystem.repository.TicketRepository;
import com.eventbooking.eventbookingsystem.repository.VenueRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VenueService {

    private final VenueRepository venueRepository;
    private final BookingRepository bookingRepository;
    private final EventRepository eventRepository;
    private final TicketRepository ticketRepository;

    public VenueService(VenueRepository venueRepository,
                        BookingRepository bookingRepository,
                        EventRepository eventRepository,
                        TicketRepository ticketRepository) {
        this.venueRepository = venueRepository;
        this.bookingRepository = bookingRepository;
        this.eventRepository = eventRepository;
        this.ticketRepository = ticketRepository;
    }

    public Venue createVenue(Venue venue) {
        return venueRepository.save(venue);
    }

    public List<Venue> getAllVenues() {
        return venueRepository.findAll();
    }

    public Optional<Venue> getVenueById(Long venueId) {
        return venueRepository.findById(venueId);
    }

    public Venue getVenueByEvent(Long eventId) {

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        return event.getVenue();
    }

    public Venue getVenueByBooking(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        return booking.getEvent().getVenue();
    }

    public Venue getVenueByTicket(Long ticketId) {

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        return ticket.getEvent().getVenue();
    }

}
