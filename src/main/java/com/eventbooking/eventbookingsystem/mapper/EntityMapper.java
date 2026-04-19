package com.eventbooking.eventbookingsystem.mapper;

import com.eventbooking.eventbookingsystem.dto.*;
import com.eventbooking.eventbookingsystem.entity.*;

public class EntityMapper {

    // User
    public static UserDTO toUserDTO(User user) {
        if (user == null) return null;

        return new UserDTO(
                user.getUserId(),
                user.getName(),
                user.getEmail()
        );
    }

    // Venue
    public static VenueDTO toVenueDTO(Venue venue) {
        if (venue == null) return null;

        return new VenueDTO(
                venue.getVenueId(),
                venue.getName(),
                venue.getLocation(),
                venue.getCapacity()
        );
    }

    // Event
    public static EventDTO toEventDTO(Event event) {
        if (event == null) return null;

        return new EventDTO(
                event.getEventId(),
                event.getName(),
                event.getEventDate(),
                event.getVenue().getVenueId(),
                event.getDescription()
        );
    }

    // Seat
    public static SeatDTO toSeatDTO(Seat seat) {
        if (seat == null) return null;

        return new SeatDTO(
                seat.getSeatId(),
                seat.getSeatNumber(),
                seat.getRowNumber(),
                seat.getStatus(),
                seat.getVenue().getVenueId()
        );
    }

    // Booking
    public static BookingDTO toBookingDTO(Booking booking) {
        if (booking == null) return null;

        return new BookingDTO(
                booking.getBookingId(),
                booking.getUser().getUserId(),
                booking.getEvent().getEventId(),
                booking.getStatus()
        );
    }

    // Ticket
    public static TicketDTO toTicketDTO(Ticket ticket) {
        if (ticket == null) return null;

        return new TicketDTO(
                ticket.getTicketId(),
                ticket.getBooking().getBookingId(),
                ticket.getSeat().getSeatId(),
                ticket.getEvent().getEventId(),
                ticket.getStatus()
        );
    }
}
