package com.eventbooking.eventbookingsystem.repository;

import com.eventbooking.eventbookingsystem.entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VenueRepository extends JpaRepository<Venue, Long> {
}