package com.eventbooking.eventbookingsystem.dto;

import com.eventbooking.eventbookingsystem.enums.TicketStatus;

public class TicketDTO {
    private Long id;
    private Long bookingId;
    private Long seatId;
    private Long eventId;
    private TicketStatus status;

    public TicketDTO() {}

    public TicketDTO(Long id, Long bookingId, Long seatId, Long eventId, TicketStatus status) {
        this.id = id;
        this.bookingId = bookingId;
        this.seatId = seatId;
        this.eventId = eventId;
        this.status = status;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Long getBookingId() { return bookingId; }

    public void setBookingId(Long bookingId) { this.bookingId = bookingId; }

    public Long getSeatId() { return seatId; }

    public void setSeatId(Long seatId) { this.seatId = seatId; }

    public Long getEventId() { return eventId; }

    public void setEventId(Long eventId) { this.eventId = eventId; }

    public TicketStatus getStatus() { return status; }

    public void setStatus(TicketStatus status) { this.status = status; }
}
