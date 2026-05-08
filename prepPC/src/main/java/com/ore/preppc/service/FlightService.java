package com.ore.preppc.service;

import com.ore.preppc.dto.NewFlightRequestDTO;
import com.ore.preppc.entity.Flight;
import com.ore.preppc.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;

    public Flight create(NewFlightRequestDTO dto) {

        if (!dto.getFlightNumber()
                .matches("^[A-Z]{2,3}[0-9]{3}$")) {
            throw new RuntimeException("Invalid flight number");
        }
        if (dto.getAvailableSeats() <= 0) {
            throw new RuntimeException("Invalid seats");
        }
        if (flightRepository.findByFlightNumber(
                dto.getFlightNumber()
        ).isPresent()) {

            throw new RuntimeException(
                    "Flight number already exists"
            );
        }

        Instant departure = Instant.parse(dto.getEstDepartureTime());

        Instant arrival = Instant.parse(dto.getEstArrivalTime());

        if (!departure.isBefore(arrival)) {
            throw new RuntimeException("Invalid dates");
        }

        Flight flight = new Flight();
        flight.setAirlineName(dto.getAirlineName());
        flight.setFlightNumber(dto.getFlightNumber());
        flight.setEstDepartureTime(departure);
        flight.setEstArrivalTime(arrival);
        flight.setAvailableSeats(dto.getAvailableSeats());

        return flightRepository.save(flight);
    }

    public List<Flight> search(String flightNumber, String airlineName) {
        return flightRepository
                .findByFlightNumberContainingIgnoreCaseAndAirlineNameContainingIgnoreCase(
                        flightNumber,
                        airlineName
                );
    }

    public List<Flight> createMany(List<NewFlightRequestDTO> dtos) {

        List<Flight> flights = new java.util.ArrayList<>();

        for (NewFlightRequestDTO dto : dtos) {
            flights.add(create(dto));
        }

        return flights;
    }


    public Flight getById(String id) {

        return flightRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("Flight not found")
                );
    }

}