package com.eventbooking.eventbookingsystem.service;

import com.eventbooking.eventbookingsystem.entity.Venue;
import com.eventbooking.eventbookingsystem.repository.VenueRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VenueService {

    private final VenueRepository venueRepository;

    public VenueService(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    public Venue createVenue(Venue venue) {
        return venueRepository.save(venue);
    }

    public List<Venue> getAllVenues() {
        return venueRepository.findAll();
    }

    public Optional<Venue> getVenueById(Long venueId) {
        return venueRepository.findById(venueId);
    }
}
