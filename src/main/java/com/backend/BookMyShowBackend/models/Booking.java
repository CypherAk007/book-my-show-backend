package com.backend.BookMyShowBackend.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Booking extends BaseModel{
    private BookingStatus status;
    private List<ShowSeat> showSeats;
    private User user;
    private Date time;
    private MovieShow movieShow;
    private double amount;
    private List<Payment> payments;

}
