package com.ore.preppc.events;

import com.ore.preppc.entity.Booking;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;

@Component
public class BookingEmailListener {

    @EventListener
    public void handleBookingCreated(
            BookingCreatedEvent event
    ) {

        Booking booking = event.getBooking();

        String filename =
                "target"
                        + java.io.File.separator
                        + "flight_booking_email_"
                        + booking.getId()
                        + ".txt";

        String content =
                "Hello "
                        + booking.getCustomerFirstName()
                        + " "
                        + booking.getCustomerLastName()
                        + ",\n\n"

                        + "Your booking was successful!\n\n"

                        + "The booking is for flight "
                        + booking.getFlight().getFlightNumber()
                        + " with departure date of "
                        + booking.getFlight()
                        .getEstDepartureTime()
                        + " and arrival date of "
                        + booking.getFlight()
                        .getEstArrivalTime()
                        + ".\n\n"

                        + "The booking was registered at "
                        + booking.getBookingDate()
                        .truncatedTo(
                                java.time.temporal.ChronoUnit.MICROS
                        )
                        .toString()
                        + ".\n\n"

                        + "Bon Voyage!\n"
                        + "Fly Away Travel";

        try (FileWriter writer =
                     new FileWriter(filename)) {

            writer.write(content);

        } catch (IOException e) {

            throw new RuntimeException(e);
        }
    }
}