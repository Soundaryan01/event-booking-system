package com.eventbooking.eventbookingsystem.entity;
import com.eventbooking.eventbookingsystem.enums.SeatStatus;
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

    @Enumerated(EnumType.STRING)
    private SeatStatus status;
}
