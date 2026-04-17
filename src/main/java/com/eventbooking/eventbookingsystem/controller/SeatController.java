package com.eventbooking.eventbookingsystem.controller;

import com.eventbooking.eventbookingsystem.entity.Seat;
import com.eventbooking.eventbookingsystem.service.SeatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seats")
public class SeatController {

    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @PostMapping("/venue/{venueId}")
    public Seat createSeat(@RequestBody Seat seat, @PathVariable Long venueId) {
        return seatService.createSeat(seat, venueId);
    }

    @GetMapping("/venue/{venueId}")
    public List<Seat> getSeatsByVenue(@PathVariable Long venueId) {
        return seatService.getSeatsByVenue(venueId);
    }

    @GetMapping("/event/{eventId}")
    public List<Seat> getSeatsByEvent(@PathVariable Long eventId) {
        return seatService.getSeatsByEvent(eventId);
    }

    @GetMapping("/event/{eventId}/available")
    public List<Seat> getAvailableSeatsByEvent(@PathVariable Long eventId) {
        return seatService.getAvailableSeatsByEvent(eventId);
    }
}
