package com.backend.BookMyShowBackend.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ShowSeatType extends BaseModel{
    private MovieShow movieShow;
    private SeatType seatType;
    private double price;
}
