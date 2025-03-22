package com.backend.BookMyShowBackend.repositories;

import com.backend.BookMyShowBackend.models.MovieShow;
import com.backend.BookMyShowBackend.models.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface showSeatTypeRepository extends JpaRepository<ShowSeatType,Long> {
    List<ShowSeatType> findByMovieShow(MovieShow movieShow);
}
