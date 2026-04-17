package com.eventbooking.eventbookingsystem.service;

import com.eventbooking.eventbookingsystem.entity.Booking;
import com.eventbooking.eventbookingsystem.entity.Event;
import com.eventbooking.eventbookingsystem.entity.Seat;
import com.eventbooking.eventbookingsystem.entity.Ticket;
import com.eventbooking.eventbookingsystem.repository.BookingRepository;
import com.eventbooking.eventbookingsystem.repository.EventRepository;
import com.eventbooking.eventbookingsystem.repository.SeatRepository;
import com.eventbooking.eventbookingsystem.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final BookingRepository bookingRepository;
    private final SeatRepository seatRepository;
    private final EventRepository eventRepository;

    public TicketService(TicketRepository ticketRepository,
                         BookingRepository bookingRepository,
                         SeatRepository seatRepository,
                         EventRepository eventRepository) {
        this.ticketRepository = ticketRepository;
        this.bookingRepository = bookingRepository;
        this.seatRepository = seatRepository;
        this.eventRepository = eventRepository;
    }

    public Ticket createTicket(Long bookingId, Long seatId, Long eventId) {

        Optional<Booking> booking = bookingRepository.findById(bookingId);
        Optional<Seat> seat = seatRepository.findById(seatId);
        Optional<Event> event = eventRepository.findById(eventId);

        if (booking.isEmpty() || seat.isEmpty() || event.isEmpty()) {
            throw new RuntimeException("Booking, Seat, or Event not found");
        }

        Ticket ticket = Ticket.builder()
                .booking(booking.get())
                .seat(seat.get())
                .event(event.get())
                .ticketStatus("CONFIRMED")
                .build();

        return ticketRepository.save(ticket);
    }
}
