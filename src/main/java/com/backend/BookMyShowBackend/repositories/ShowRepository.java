package com.backend.BookMyShowBackend.repositories;


import com.backend.BookMyShowBackend.models.MovieShow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<MovieShow,Long> {

}
