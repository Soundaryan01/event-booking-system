package com.eventbooking.eventbookingsystem.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "seats")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatId;

    private String seatNumber;

    private String rowNumber;

    @ManyToOne
    @JoinColumn(name = "venue_id")
    private Venue venue;
}
