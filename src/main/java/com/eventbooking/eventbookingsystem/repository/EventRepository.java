package com.eventbooking.eventbookingsystem.repository;

import com.eventbooking.eventbookingsystem.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
