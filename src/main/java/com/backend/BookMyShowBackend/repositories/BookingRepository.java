package com.backend.BookMyShowBackend.repositories;


import com.backend.BookMyShowBackend.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking,Long> {
}
