package com.eventbooking.eventbookingsystem.controller;

import com.eventbooking.eventbookingsystem.entity.Booking;
import com.eventbooking.eventbookingsystem.entity.Ticket;
import com.eventbooking.eventbookingsystem.service.BookingService;
import com.eventbooking.eventbookingsystem.service.TicketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;
    private final BookingService bookingService;

    public TicketController(TicketService ticketService,  BookingService bookingService) {
        this.ticketService = ticketService;
        this.bookingService = bookingService;
    }

    @PostMapping("/booking/{bookingId}/seat/{seatId}")
    public Ticket createTicket(@PathVariable Long bookingId, @PathVariable Long seatId) {
        Optional<Booking> booking = bookingService.getBookingById(bookingId);
        if(booking.isPresent()) {
            return ticketService.createTicket(bookingId, seatId, booking.get().getEvent().getEventId());
        }else{
            throw new RuntimeException("Invalid booking id");
        }
    }

    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/{ticketId}")
    public Ticket getTicketById(@PathVariable Long ticketId) {
        Optional<Ticket> ticket =  ticketService.getTicketById(ticketId);
        return ticket.orElse(null);
    }

    @GetMapping("/booking/{bookingId}")
    public List<Ticket> getTicketsByBooking(@PathVariable Long bookingId) {
        return ticketService.getTicketsByBooking(bookingId);
    }

    @GetMapping("/event/{eventId}")
    public List<Ticket> getTicketsByEvent(@PathVariable Long eventId) {
        return ticketService.getTicketsByEvent(eventId);
    }

    @PutMapping("/{ticketId}/cancel")
    public Ticket cancelTicket(@PathVariable Long ticketId) {
        return ticketService.cancelTicket(ticketId);
    }
}
