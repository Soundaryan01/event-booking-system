package com.eventbooking.eventbookingsystem.dto;

import com.eventbooking.eventbookingsystem.enums.BookingStatus;

public class BookingDTO {
    private Long id;
    private Long userId;
    private Long eventId;
    private BookingStatus status;

    public BookingDTO() {}

    public BookingDTO(Long id, Long userId, Long eventId, BookingStatus status) {
        this.id = id;
        this.userId = userId;
        this.eventId = eventId;
        this.status = status;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }

    public void setUserId(Long userId) { this.userId = userId; }

    public Long getEventId() { return eventId; }

    public void setEventId(Long eventId) { this.eventId = eventId; }

    public BookingStatus getStatus() { return status; }

    public void setStatus(BookingStatus status) { this.status = status; }
}
