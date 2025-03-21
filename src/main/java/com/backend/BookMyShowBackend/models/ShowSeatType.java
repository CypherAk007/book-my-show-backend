package com.backend.BookMyShowBackend.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShowSeatType extends BaseModel{
    private MovieShow movieShow;
    private SeatType seatType;
    private double price;
}
