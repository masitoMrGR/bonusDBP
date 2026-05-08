package com.ore.preppc.events;

import com.ore.preppc.entity.Booking;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookingCreatedEvent {

    private Booking booking;
}