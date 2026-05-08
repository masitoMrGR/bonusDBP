package com.ore.preppc.controller;
import org.springframework.http.HttpStatus;
import com.ore.preppc.dto.NewFlightRequestDTO;
import com.ore.preppc.dto.NewIdDTO;
import com.ore.preppc.entity.Flight;
import com.ore.preppc.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.ore.preppc.dto.CreateManyFlightsDTO;
@RestController
@RequestMapping("/flights")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    @PostMapping("/create")
    public ResponseEntity<NewIdDTO> create(@RequestBody NewFlightRequestDTO dto) {

        Flight flight = flightService.create(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        new NewIdDTO(flight.getId())
                );
    }

    @GetMapping("/search")
    public ResponseEntity<List<Flight>> search(
            @RequestParam(defaultValue = "") String flightNumber,
            @RequestParam(defaultValue = "") String airlineName
    ) {
        return ResponseEntity.ok(
                flightService.search(flightNumber, airlineName)
        );
    }

    @PostMapping("/create-many")
    public ResponseEntity<List<NewIdDTO>> createMany(
            @RequestBody CreateManyFlightsDTO dto
    ) {

        List<Flight> flights = flightService.createMany(dto.getFlights());

        List<NewIdDTO> response = flights.stream()
                .map(f -> new NewIdDTO(f.getId()))
                .toList();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlight(
            @PathVariable String id
    ) {

        return ResponseEntity.ok(
                flightService.getById(id)
        );
    }

}