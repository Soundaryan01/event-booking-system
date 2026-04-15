package com.eventbooking.eventbookingsystem.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "venues")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Venue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long venueId;

    private String name;

    private String location;

    private Integer capacity;
}
