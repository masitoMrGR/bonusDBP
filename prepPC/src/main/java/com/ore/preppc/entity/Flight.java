package com.ore.preppc.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String airlineName;

    @Column(unique = true)
    private String flightNumber;

    private Instant estDepartureTime;

    private Instant estArrivalTime;

    private Integer availableSeats;
}