package com.ore.preppc.dto;
import lombok.*;

@Getter
@Setter
public class NewFlightRequestDTO {
    private String airlineName;
    private String flightNumber;
    private String estDepartureTime;
    private String estArrivalTime;
    private Integer availableSeats;
}