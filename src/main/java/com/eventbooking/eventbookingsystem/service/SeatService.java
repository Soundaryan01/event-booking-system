package com.eventbooking.eventbookingsystem.service;

import com.eventbooking.eventbookingsystem.entity.Event;
import com.eventbooking.eventbookingsystem.entity.Seat;
import com.eventbooking.eventbookingsystem.entity.Ticket;
import com.eventbooking.eventbookingsystem.entity.Venue;
import com.eventbooking.eventbookingsystem.repository.EventRepository;
import com.eventbooking.eventbookingsystem.repository.SeatRepository;
import com.eventbooking.eventbookingsystem.repository.TicketRepository;
import com.eventbooking.eventbookingsystem.repository.VenueRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeatService {

    private final SeatRepository seatRepository;
    private final VenueRepository venueRepository;
    private final TicketRepository ticketRepository;
    private final EventRepository eventRepository;

    public SeatService(SeatRepository seatRepository, VenueRepository venueRepository,
                       TicketRepository ticketRepository, EventRepository eventRepository) {
        this.seatRepository = seatRepository;
        this.venueRepository = venueRepository;
        this.ticketRepository = ticketRepository;
        this.eventRepository = eventRepository;
    }

    public Seat createSeat(Seat seat, Long venueId) {

        Optional<Venue> venue = venueRepository.findById(venueId);

        if (venue.isEmpty()) {
            throw new RuntimeException("Venue not found");
        }

        seat.setVenue(venue.get());

        return seatRepository.save(seat);
    }

    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }

    public Optional<Seat> getSeatById(Long seatId) {
        return seatRepository.findById(seatId);
    }

    public List<Seat> getSeatsByVenue(Long venueId) {

        Venue venue = venueRepository.findById(venueId)
                .orElseThrow(() -> new RuntimeException("Venue not found"));

        return seatRepository.findAll()
                .stream()
                .filter(seat -> seat.getVenue().equals(venue))
                .toList();
    }

    public List<Seat> getSeatsByEvent(Long eventId) {

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        Venue venue = event.getVenue();

        return seatRepository.findAll()
                .stream()
                .filter(seat -> seat.getVenue().equals(venue))
                .toList();
    }

    public List<Seat> getAvailableSeatsByEvent(Long eventId) {

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        List<Ticket> bookedTickets = ticketRepository.findAll()
                .stream()
                .filter(ticket -> ticket.getEvent().equals(event)
                        && !"CANCELLED".equals(ticket.getTicketStatus()))
                .toList();

        List<Seat> bookedSeats = bookedTickets.stream()
                .map(Ticket::getSeat)
                .toList();

        return getSeatsByEvent(eventId)
                .stream()
                .filter(seat -> !bookedSeats.contains(seat))
                .toList();
    }

}
