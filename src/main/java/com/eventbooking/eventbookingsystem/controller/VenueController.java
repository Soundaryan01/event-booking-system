package com.eventbooking.eventbookingsystem.controller;

import com.eventbooking.eventbookingsystem.entity.Venue;
import com.eventbooking.eventbookingsystem.service.VenueService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/venues")
public class VenueController {

    private final VenueService venueService;

    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    @PostMapping
    public Venue createVenue(@RequestBody Venue venue) {
        return venueService.createVenue(venue);
    }

    @GetMapping
    public List<Venue> getAllVenues() {
        return venueService.getAllVenues();
    }

    @GetMapping("/{id}")
    public Venue getVenueById(@PathVariable Long id) {
        Optional<Venue> venue = venueService.getVenueById(id);
        return venue.orElse(null);
    }

    @GetMapping("/event/{eventId}")
    public Venue getVenueByEvent(@PathVariable Long eventId) {
        return venueService.getVenueByEvent(eventId);
    }

    @GetMapping("/booking/{bookingId}")
    public Venue getVenueByBooking(@PathVariable Long bookingId) {
        return venueService.getVenueByBooking(bookingId);
    }

    @GetMapping("/ticket/{ticketId}")
    public Venue getVenueByTicket(@PathVariable Long ticketId) {
        return venueService.getVenueByTicket(ticketId);
    }
}
