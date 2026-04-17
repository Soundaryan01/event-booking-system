package com.eventbooking.eventbookingsystem.controller;

import com.eventbooking.eventbookingsystem.entity.Event;
import com.eventbooking.eventbookingsystem.service.EventService;
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
    public Event createEvent(@RequestBody Event event, @PathVariable Long venueId) {
        return eventService.createEvent(event, venueId);
    }

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{eventId}")
    public Event getEventById(@PathVariable Long eventId) {
        Optional<Event> event = eventService.getEventById(eventId);
        return event.orElse(null);
    }

    @PutMapping("/{eventId}")
    public Event updateEvent(@PathVariable Long eventId, @RequestBody Event event) {
        return eventService.updateEvent(eventId, event);
    }

    @DeleteMapping("/{eventId}")
    public void deleteEvent(@PathVariable Long eventId) {
        eventService.deleteEvent(eventId);
    }

    @GetMapping("/venue/{venueId}")
    public List<Event> getEventsByVenue(@PathVariable Long venueId) {
        return eventService.getEventsByVenue(venueId);
    }

    @GetMapping("/upcoming")
    public List<Event> getUpcomingEvents() {
        return eventService.getUpcomingEvents();
    }
}
