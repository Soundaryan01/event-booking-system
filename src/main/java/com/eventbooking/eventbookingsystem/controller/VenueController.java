package com.eventbooking.eventbookingsystem.controller;

import com.eventbooking.eventbookingsystem.dto.response.VenueDTO;
import com.eventbooking.eventbookingsystem.entity.Venue;
import com.eventbooking.eventbookingsystem.mapper.EntityMapper;
import com.eventbooking.eventbookingsystem.service.VenueService;
import com.eventbooking.eventbookingsystem.wrapper.APIResponse;
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
    public APIResponse<VenueDTO> createVenue(@RequestBody Venue venue) {

        Venue saved = venueService.createVenue(venue);

        return new APIResponse<>(
                true,
                "Venue created successfully",
                EntityMapper.toVenueDTO(saved)
        );
    }

    @GetMapping
    public APIResponse<List<VenueDTO>> getAllVenues() {

        List<VenueDTO> venues = venueService.getAllVenues()
                .stream()
                .map(EntityMapper::toVenueDTO)
                .toList();

        return new APIResponse<>(
                true,
                "Venues fetched successfully",
                venues
        );
    }

    @GetMapping("/{id}")
    public APIResponse<VenueDTO> getVenueById(@PathVariable Long id) {

        Optional<Venue> venue = venueService.getVenueById(id);
        if(venue.isEmpty()){
            throw new RuntimeException("Venue not found: " + id );
        }

        return new APIResponse<>(
                true,
                "Venue fetched successfully",
                EntityMapper.toVenueDTO(venue.get())
        );
    }

    @GetMapping("/event/{eventId}")
    public APIResponse<VenueDTO> getVenueByEvent(@PathVariable Long eventId) {

        Venue venue = venueService.getVenueByEvent(eventId);

        return new APIResponse<>(
                true,
                "Venue fetched by event",
                EntityMapper.toVenueDTO(venue)
        );
    }

    @GetMapping("/booking/{bookingId}")
    public APIResponse<VenueDTO> getVenueByBooking(@PathVariable Long bookingId) {

        Venue venue = venueService.getVenueByBooking(bookingId);

        return new APIResponse<>(
                true,
                "Venue fetched by booking",
                EntityMapper.toVenueDTO(venue)
        );
    }

    @GetMapping("/ticket/{ticketId}")
    public APIResponse<VenueDTO> getVenueByTicket(@PathVariable Long ticketId) {

        Venue venue = venueService.getVenueByTicket(ticketId);

        return new APIResponse<>(
                true,
                "Venue fetched by ticket",
                EntityMapper.toVenueDTO(venue)
        );
    }
}
