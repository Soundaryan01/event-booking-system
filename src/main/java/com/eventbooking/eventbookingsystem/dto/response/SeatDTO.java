package com.eventbooking.eventbookingsystem.dto.response;

import com.eventbooking.eventbookingsystem.enums.SeatStatus;

public class SeatDTO {
    private Long id;
    private String seatNumber;
    private String rowNumber;
    private SeatStatus status;
    private Long venueId;

    public SeatDTO() {}

    public SeatDTO(Long id, String seatNumber, String rowNumber, SeatStatus status, Long venueId) {
        this.id = id;
        this.seatNumber = seatNumber;
        this.rowNumber = rowNumber;
        this.status = status;
        this.venueId = venueId;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getSeatNumber() { return seatNumber; }

    public void setSeatNumber(String seatNumber) { this.seatNumber = seatNumber; }

    public String getRowNumber() { return rowNumber; }

    public void setRowNumber(String rowNumber) { this.rowNumber = rowNumber; }

    public SeatStatus getStatus() { return status; }

    public void setStatus(SeatStatus status) { this.status = status; }

    public Long getVenueId() { return venueId; }

    public void setVenueId(Long venueId) { this.venueId = venueId; }

}
