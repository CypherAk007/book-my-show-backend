package com.backend.BookMyShowBackend.repositories;

import com.backend.BookMyShowBackend.models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowSeatRepository extends JpaRepository<ShowSeat,Long> {
}
