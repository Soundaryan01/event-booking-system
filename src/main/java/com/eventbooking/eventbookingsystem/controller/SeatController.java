package com.eventbooking.eventbookingsystem.controller;

import com.eventbooking.eventbookingsystem.dto.SeatDTO;
import com.eventbooking.eventbookingsystem.entity.Seat;
import com.eventbooking.eventbookingsystem.mapper.EntityMapper;
import com.eventbooking.eventbookingsystem.service.SeatService;
import com.eventbooking.eventbookingsystem.wrapper.APIResponse;
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
    public APIResponse<SeatDTO> createSeat(@RequestBody Seat seat,
                                           @PathVariable Long venueId) {

        Seat created = seatService.createSeat(seat, venueId);

        return new APIResponse<>(
                true,
                "Seat created successfully",
                EntityMapper.toSeatDTO(created)
        );
    }

    @GetMapping("/venue/{venueId}")
    public APIResponse<List<SeatDTO>> getSeatsByVenue(@PathVariable Long venueId) {

        List<SeatDTO> seats = seatService.getSeatsByVenue(venueId)
                .stream()
                .map(EntityMapper::toSeatDTO)
                .toList();

        return new APIResponse<>(
                true,
                "Seats fetched by venue",
                seats
        );
    }

    @GetMapping("/event/{eventId}")
    public APIResponse<List<SeatDTO>> getSeatsByEvent(@PathVariable Long eventId) {

        List<SeatDTO> seats = seatService.getSeatsByEvent(eventId)
                .stream()
                .map(EntityMapper::toSeatDTO)
                .toList();

        return new APIResponse<>(
                true,
                "Seats fetched by event",
                seats
        );
    }

    @GetMapping("/event/{eventId}/available")
    public APIResponse<List<SeatDTO>> getAvailableSeatsByEvent(@PathVariable Long eventId) {

        List<SeatDTO> seats = seatService.getAvailableSeatsByEvent(eventId)
                .stream()
                .map(EntityMapper::toSeatDTO)
                .toList();

        return new APIResponse<>(
                true,
                "Available seats fetched",
                seats
        );
    }
}
