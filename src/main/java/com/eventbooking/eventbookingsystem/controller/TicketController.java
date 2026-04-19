package com.eventbooking.eventbookingsystem.controller;

import com.eventbooking.eventbookingsystem.dto.TicketDTO;
import com.eventbooking.eventbookingsystem.entity.Booking;
import com.eventbooking.eventbookingsystem.entity.Ticket;
import com.eventbooking.eventbookingsystem.mapper.EntityMapper;
import com.eventbooking.eventbookingsystem.service.BookingService;
import com.eventbooking.eventbookingsystem.service.TicketService;
import com.eventbooking.eventbookingsystem.wrapper.APIResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/booking/{bookingId}/seat/{seatId}")
    public APIResponse<TicketDTO> createTicket(@PathVariable Long bookingId,
                                               @PathVariable Long seatId) {

        Ticket ticket = ticketService.createTicket(bookingId, seatId);

        return new APIResponse<>(
                true,
                "Ticket created successfully",
                EntityMapper.toTicketDTO(ticket)
        );
    }

    @GetMapping
    public APIResponse<List<TicketDTO>> getAllTickets() {

        List<TicketDTO> tickets = ticketService.getAllTickets()
                .stream()
                .map(EntityMapper::toTicketDTO)
                .toList();

        return new APIResponse<>(
                true,
                "Tickets fetched successfully",
                tickets
        );
    }

    @GetMapping("/{ticketId}")
    public APIResponse<TicketDTO> getTicketById(@PathVariable Long ticketId) {

        Optional<Ticket> ticket = ticketService.getTicketById(ticketId);
        if(ticket.isEmpty()){
            throw new RuntimeException("Ticket not found: "+ticketId);
        }

        return new APIResponse<>(
                true,
                "Ticket fetched successfully",
                EntityMapper.toTicketDTO(ticket.get())
        );
    }

    @GetMapping("/booking/{bookingId}")
    public APIResponse<List<TicketDTO>> getTicketsByBooking(@PathVariable Long bookingId) {

        List<TicketDTO> tickets = ticketService.getTicketsByBooking(bookingId)
                .stream()
                .map(EntityMapper::toTicketDTO)
                .toList();

        return new APIResponse<>(
                true,
                "Tickets fetched by booking",
                tickets
        );
    }

    @PutMapping("/{ticketId}/cancel")
    public APIResponse<TicketDTO> cancelTicket(@PathVariable Long ticketId) {

        Ticket ticket = ticketService.cancelTicket(ticketId);

        return new APIResponse<>(
                true,
                "Ticket cancelled successfully",
                EntityMapper.toTicketDTO(ticket)
        );
    }
}
