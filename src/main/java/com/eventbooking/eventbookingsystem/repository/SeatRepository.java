package com.eventbooking.eventbookingsystem.repository;

import com.eventbooking.eventbookingsystem.entity.Event;
import com.eventbooking.eventbookingsystem.entity.Seat;
import com.eventbooking.eventbookingsystem.enums.SeatStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByEventAndStatus(Event event, SeatStatus status);
}