package com.ore.preppc.service;
import java.util.List;
import com.ore.preppc.events.BookingCreatedEvent;
import org.springframework.context.ApplicationEventPublisher;
import com.ore.preppc.dto.BookingResponseDTO;
import com.ore.preppc.dto.FlightBookRequestDTO;
import com.ore.preppc.entity.Booking;
import com.ore.preppc.entity.Flight;
import com.ore.preppc.entity.User;
import com.ore.preppc.repository.BookingRepository;
import com.ore.preppc.repository.FlightRepository;
import com.ore.preppc.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final FlightRepository flightRepository;
    private final UserRepository userRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public Booking createBooking(
            FlightBookRequestDTO dto,
            String email
    ) {
        User user = userRepository.findByEmail(email)
                .orElseThrow();

        Flight flight = flightRepository.findById(dto.getFlightId())
                .orElseThrow();
        Instant now = Instant.now();

        if (!flight.getEstDepartureTime().isAfter(now)) {

            throw new RuntimeException(
                    "Flight already departed or in progress"
            );
        }
        List<Booking> userBookings =
                bookingRepository.findByCustomerId(
                        user.getId()
                );

        for (Booking existingBooking : userBookings) {

            Flight existingFlight =
                    existingBooking.getFlight();

            boolean overlaps =

                    flight.getEstDepartureTime()
                            .isBefore(
                                    existingFlight.getEstArrivalTime()
                            )

                            &&

                            flight.getEstArrivalTime()
                                    .isAfter(
                                            existingFlight.getEstDepartureTime()
                                    );

            if (overlaps) {

                throw new RuntimeException(
                        "User already has overlapping flight"
                );
            }
        }
        if (flight.getAvailableSeats() <= 0) {
            throw new RuntimeException("No seats available");
        }

        flight.setAvailableSeats(
                flight.getAvailableSeats() - 1
        );

        Booking booking = new Booking();

        booking.setBookingDate(Instant.now());

        booking.setCustomerId(user.getId());
        booking.setCustomerFirstName(user.getFirstName());
        booking.setCustomerLastName(user.getLastName());

        booking.setFlight(flight);

        Booking savedBooking =
                bookingRepository.save(booking);

        eventPublisher.publishEvent(
                new BookingCreatedEvent(savedBooking)
        );

        return savedBooking;
    }


    public BookingResponseDTO getBookingDTO(String id) {

        Booking booking = bookingRepository.findById(id)
                .orElseThrow();

        BookingResponseDTO dto =
                new BookingResponseDTO();

        dto.setId(booking.getId());

        dto.setBookingDate(
                booking.getBookingDate().toString()
        );

        dto.setFlightId(
                booking.getFlight().getId()
        );

        dto.setFlightNumber(
                booking.getFlight().getFlightNumber()
        );

        dto.setEstDepartureTime(
                booking.getFlight()
                        .getEstDepartureTime()
                        .toString()
        );

        dto.setEstArrivalTime(
                booking.getFlight()
                        .getEstArrivalTime()
                        .toString()
        );

        dto.setCustomerId(
                booking.getCustomerId()
        );

        dto.setCustomerFirstName(
                booking.getCustomerFirstName()
        );

        dto.setCustomerLastName(
                booking.getCustomerLastName()
        );

        return dto;
    }
}