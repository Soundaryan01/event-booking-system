package com.eventbooking.eventbookingsystem.service;

import com.eventbooking.eventbookingsystem.entity.Event;
import com.eventbooking.eventbookingsystem.entity.Venue;
import com.eventbooking.eventbookingsystem.repository.EventRepository;
import com.eventbooking.eventbookingsystem.repository.VenueRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final VenueRepository venueRepository;

    public EventService(EventRepository eventRepository, VenueRepository venueRepository) {
        this.eventRepository = eventRepository;
        this.venueRepository = venueRepository;
    }

    public Event createEvent(Event event, Long venueId) {
        Optional<Venue> venue = venueRepository.findById(venueId);
        if (venue.isEmpty()) {
            throw new RuntimeException("Venue not found");
        }
        event.setVenue(venue.get());
        event.setCreatedAt(LocalDateTime.now());
        return eventRepository.save(event);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Optional<Event> getEventById(Long eventId) {
        return eventRepository.findById(eventId);
    }

    public Event updateEvent(Long eventId, Event updatedEvent) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        event.setName(updatedEvent.getName());
        event.setDescription(updatedEvent.getDescription());
        event.setEventDate(updatedEvent.getEventDate());
        return eventRepository.save(event);
    }

    public void deleteEvent(Long eventId) {
        if (!eventRepository.existsById(eventId)) {
            throw new RuntimeException("Event not found");
        }
        eventRepository.deleteById(eventId);
    }

    public List<Event> getEventsByVenue(Long venueId) {
        Venue venue = venueRepository.findById(venueId)
                .orElseThrow(() -> new RuntimeException("Venue not found"));

        return eventRepository.findAll()
                .stream()
                .filter(event -> event.getVenue().equals(venue))
                .toList();
    }

    public List<Event> getUpcomingEvents() {
        return eventRepository.findAll()
                .stream()
                .filter(event -> event.getEventDate().isAfter(LocalDateTime.now()))
                .toList();
    }

}