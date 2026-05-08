package com.ore.preppc.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private Instant bookingDate;

    private String customerId;

    private String customerFirstName;

    private String customerLastName;

    @ManyToOne
    private Flight flight;
}