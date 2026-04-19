package com.eventbooking.eventbookingsystem.dto.request;

import jakarta.validation.constraints.NotNull;

public class CreateTicketRequest {

    @NotNull(message = "Booking ID is required")
    private Long bookingId;

    @NotNull(message = "Seat ID is required")
    private Long seatId;

    public CreateTicketRequest() {}

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }
}
