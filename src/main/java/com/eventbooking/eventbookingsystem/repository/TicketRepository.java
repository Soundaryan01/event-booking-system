package com.eventbooking.eventbookingsystem.repository;

import com.eventbooking.eventbookingsystem.entity.Booking;
import com.eventbooking.eventbookingsystem.entity.Event;
import com.eventbooking.eventbookingsystem.entity.Ticket;
import com.eventbooking.eventbookingsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByBooking(Booking booking);

    List<Ticket> findByUser(User user);

    List<Ticket> findByEvent(Event event);
}
