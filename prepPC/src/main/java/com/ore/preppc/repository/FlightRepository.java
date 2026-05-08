package com.ore.preppc.repository;

import com.ore.preppc.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, String> {

    Optional<Flight> findByFlightNumber(String flightNumber);

    List<Flight> findByFlightNumberContainingIgnoreCaseAndAirlineNameContainingIgnoreCase(
            String flightNumber,
            String airlineName
    );
    
}