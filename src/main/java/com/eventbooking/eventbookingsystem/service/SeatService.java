package com.eventbooking.eventbookingsystem.service;

import com.eventbooking.eventbookingsystem.entity.Seat;
import com.eventbooking.eventbookingsystem.entity.Venue;
import com.eventbooking.eventbookingsystem.repository.SeatRepository;
import com.eventbooking.eventbookingsystem.repository.VenueRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeatService {

    private final SeatRepository seatRepository;
    private final VenueRepository venueRepository;

    public SeatService(SeatRepository seatRepository, VenueRepository venueRepository) {
        this.seatRepository = seatRepository;
        this.venueRepository = venueRepository;
    }

    public Seat createSeat(Seat seat, Long venueId) {

        Optional<Venue> venue = venueRepository.findById(venueId);

        if (venue.isEmpty()) {
            throw new RuntimeException("Venue not found");
        }

        seat.setVenue(venue.get());

        return seatRepository.save(seat);
    }

    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }

    public Optional<Seat> getSeatById(Long seatId) {
        return seatRepository.findById(seatId);
    }
}
