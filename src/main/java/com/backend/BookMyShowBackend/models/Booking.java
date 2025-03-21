package com.backend.BookMyShowBackend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Booking extends BaseModel{
    private BookingStatus status;
//    1 booking many show seats
//    1 show seat had to be one booking but
//            we can cancle and other booking
//            can be made on same showseat

    @ManyToMany
    private List<ShowSeat> showSeats;


    private User user;
    private Date time;
    private MovieShow movieShow;
    private double amount;
    private List<Payment> payments;

}
