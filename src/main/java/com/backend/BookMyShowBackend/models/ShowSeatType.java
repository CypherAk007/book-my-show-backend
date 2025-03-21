package com.backend.BookMyShowBackend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ShowSeatType extends BaseModel{
    //same explaination as showseat
    @ManyToOne
    private MovieShow movieShow;
    @ManyToOne
    private SeatType seatType;
    private double price;
}
