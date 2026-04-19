package com.eventbooking.eventbookingsystem.service;

import com.eventbooking.eventbookingsystem.entity.*;
import com.eventbooking.eventbookingsystem.enums.BookingStatus;
import com.eventbooking.eventbookingsystem.enums.SeatStatus;
import com.eventbooking.eventbookingsystem.enums.TicketStatus;
import com.eventbooking.eventbookingsystem.repository.BookingRepository;
import com.eventbooking.eventbookingsystem.repository.EventRepository;
import com.eventbooking.eventbookingsystem.repository.SeatRepository;
import com.eventbooking.eventbookingsystem.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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
        if(seat.isEmpty()){
            throw new RuntimeException("Seat not found");
        }
        seat.get().setStatus(SeatStatus.BOOKED);
        seatRepository.save(seat.get());

        Optional<Event> event = eventRepository.findById(eventId);

        if (booking.isEmpty() || event.isEmpty()) {
            throw new RuntimeException("Booking, Seat, or Event not found");
        }



        Ticket ticket = Ticket.builder()
                .booking(booking.get())
                .seat(seat.get())
                .event(event.get())
                .status(TicketStatus.ACTIVE)
                .build();

        return ticketRepository.save(ticket);
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Optional<Ticket> getTicketById(Long ticketId) {
        return ticketRepository.findById(ticketId);
    }



    public List<Ticket> getTicketByUser(User user){
        return ticketRepository.findByUser(user);
    }

    public List<Ticket> getTicketsByBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        return ticketRepository.findAll()
                .stream()
                .filter(ticket -> ticket.getBooking().equals(booking))
                .toList();
    }

    public Ticket cancelTicket(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
        ticket.setStatus(TicketStatus.CANCELLED);
        ticketRepository.save(ticket);

        Seat seat = ticket.getSeat();
        seat.setStatus(SeatStatus.AVAILABLE);
        seatRepository.save(seat);

        Booking booking = ticket.getBooking();

        List<Ticket> tickets = ticketRepository.findByBooking(booking);

        boolean allCancelled = tickets.stream()
                .allMatch(t -> TicketStatus.CANCELLED.equals(t.getStatus()));

        if (allCancelled) {
            booking.setStatus(BookingStatus.CANCELLED);
            bookingRepository.save(booking);
        }
        return ticket;
    }

    public List<Ticket> getTicketsByEvent(Long eventId) {
        Optional<Event> event = eventRepository.findById(eventId);
        event.orElseThrow(() -> new RuntimeException("Event not found"));
        return ticketRepository.findByEvent(event.get());
    }
}
