package com.eventbooking.eventbookingsystem.dto;

import java.time.LocalDateTime;

public class EventDTO {
    private Long id;
    private String name;
    private LocalDateTime eventDate;
    private Long venueId;
    private String description;

    public EventDTO() {}

    public EventDTO(Long id, String name, LocalDateTime eventDate, Long venueId, String description) {
        this.id = id;
        this.name = name;
        this.eventDate = eventDate;
        this.venueId = venueId;
        this.description = description;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public LocalDateTime getEventDate() { return eventDate; }

    public void setEventDate(LocalDateTime eventDate) { this.eventDate = eventDate; }

    public Long getVenueId() { return venueId; }

    public void setVenueId(Long venueId) { this.venueId = venueId; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
