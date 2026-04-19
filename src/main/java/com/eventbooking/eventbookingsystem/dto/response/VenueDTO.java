package com.eventbooking.eventbookingsystem.dto.response;

public class VenueDTO {
    private Long id;
    private String name;
    private String location;
    private Integer capacity;

    public VenueDTO() {}

    public VenueDTO(Long id, String name, String location,  Integer capacity) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.capacity = capacity;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }

    public Integer getCapacity() { return capacity; }

    public void setCapacity(Integer capacity) { this.capacity = capacity; }
}
