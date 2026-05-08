package com.ore.preppc.controller;

import com.ore.preppc.repository.BookingRepository;
import com.ore.preppc.repository.FlightRepository;
import com.ore.preppc.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cleanup")
@RequiredArgsConstructor
public class CleanupController {

    private final UserRepository userRepository;
    private final FlightRepository flightRepository;
    private final BookingRepository bookingRepository;

    @DeleteMapping
    public ResponseEntity<Void> cleanup() {

        bookingRepository.deleteAll();
        flightRepository.deleteAll();
        userRepository.deleteAll();

        return ResponseEntity.ok().build();
    }
}