package com.ore.preppc.repository;
import com.ore.preppc.entity.Booking;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository
        extends JpaRepository<Booking, String> {

    List<Booking> findByCustomerId(String customerId);
}