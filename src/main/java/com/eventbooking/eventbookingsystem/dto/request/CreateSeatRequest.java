package com.eventbooking.eventbookingsystem.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateSeatRequest {

    @NotBlank(message = "Seat number cannot be empty")
    @Size(max = 10, message = "Seat number too long")
    private String seatNumber;

    @NotNull(message = "Venue ID is required")
    private Long venueId;

    public CreateSeatRequest() {}

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Long getVenueId() {
        return venueId;
    }

    public void setVenueId(Long venueId) {
        this.venueId = venueId;
    }
}
