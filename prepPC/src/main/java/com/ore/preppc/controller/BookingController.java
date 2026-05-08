package com.ore.preppc.controller;
import org.springframework.http.HttpStatus;
import com.ore.preppc.dto.BookingResponseDTO;
import com.ore.preppc.dto.FlightBookRequestDTO;
import com.ore.preppc.dto.NewIdDTO;
import com.ore.preppc.entity.Booking;
import com.ore.preppc.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
@RestController
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/flights/book")
    public ResponseEntity<NewIdDTO> bookFlight(
            @RequestBody FlightBookRequestDTO dto,
            Authentication authentication    ) {

        Booking booking = bookingService.createBooking(
                dto,
                authentication.getName()
        );

        return ResponseEntity.ok(
                new NewIdDTO(booking.getId())
        );
    }


    @GetMapping({"/flight/book/{id}","/flights/book/{id}"})
    public ResponseEntity<BookingResponseDTO> getBooking(
            @PathVariable String id
    ) {

        return ResponseEntity.ok(
                bookingService.getBookingDTO(id)
        );
    }
}