package com.eventbooking.eventbookingsystem.controller;

import com.eventbooking.eventbookingsystem.dto.EventDTO;
import com.eventbooking.eventbookingsystem.entity.Event;
import com.eventbooking.eventbookingsystem.mapper.EntityMapper;
import com.eventbooking.eventbookingsystem.service.EventService;
import com.eventbooking.eventbookingsystem.wrapper.APIResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/venue/{venueId}")
    public APIResponse<EventDTO> createEvent(@RequestBody Event event,
                                             @PathVariable Long venueId) {

        Event savedEvent = eventService.createEvent(event, venueId);

        return new APIResponse<>(
                true,
                "Event created successfully",
                EntityMapper.toEventDTO(savedEvent)
        );
    }

    @GetMapping
    public APIResponse<List<EventDTO>> getAllEvents() {

        List<EventDTO> events = eventService.getAllEvents()
                .stream()
                .map(EntityMapper::toEventDTO)
                .toList();

        return new APIResponse<>(
                true,
                "Events fetched successfully",
                events
        );
    }

    @GetMapping("/{eventId}")
    public APIResponse<EventDTO> getEventById(@PathVariable Long eventId) {

        Optional<Event> event = eventService.getEventById(eventId);
        if(event.isEmpty()) {
            throw new RuntimeException("Event not found: " + eventId);
        }

        return new APIResponse<>(
                true,
                "Event fetched successfully",
                EntityMapper.toEventDTO(event.get())
        );
    }

    @PutMapping("/{eventId}")
    public APIResponse<EventDTO> updateEvent(@PathVariable Long eventId,
                                             @RequestBody Event event) {

        Event updatedEvent = eventService.updateEvent(eventId, event);

        return new APIResponse<>(
                true,
                "Event updated successfully",
                EntityMapper.toEventDTO(updatedEvent)
        );
    }

    @DeleteMapping("/{eventId}")
    public APIResponse<Void> deleteEvent(@PathVariable Long eventId) {

        eventService.deleteEvent(eventId);

        return new APIResponse<>(
                true,
                "Event deleted successfully",
                null
        );
    }

    @GetMapping("/venue/{venueId}")
    public APIResponse<List<EventDTO>> getEventsByVenue(@PathVariable Long venueId) {

        List<EventDTO> events = eventService.getEventsByVenue(venueId)
                .stream()
                .map(EntityMapper::toEventDTO)
                .toList();

        return new APIResponse<>(
                true,
                "Events fetched for venue",
                events
        );
    }

    @GetMapping("/upcoming")
    public APIResponse<List<EventDTO>> getUpcomingEvents() {

        List<EventDTO> events = eventService.getUpcomingEvents()
                .stream()
                .map(EntityMapper::toEventDTO)
                .toList();

        return new APIResponse<>(
                true,
                "Upcoming events fetched",
                events
        );
    }
}
