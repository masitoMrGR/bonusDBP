package com.ore.preppc.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingResponseDTO {

    private String id;

    private String bookingDate;

    private String flightId;

    private String flightNumber;

    private String customerId;

    private String customerFirstName;

    private String customerLastName;
    
    private String estDepartureTime;

    private String estArrivalTime;
}