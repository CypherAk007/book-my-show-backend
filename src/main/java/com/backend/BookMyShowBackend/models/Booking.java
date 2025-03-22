package com.backend.BookMyShowBackend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Booking extends BaseModel{
//    @Enumerated(value = EnumType.ORDINAL)
    @Enumerated(value = EnumType.STRING)
    private BookingStatus status;
//    1 booking many show seats
//    1 show seat had to be one booking but
//            we can cancle and other booking
//            can be made on same showseat

    @ManyToMany
    private List<ShowSeat> showSeats;

//    Avoid jpa creating multiple columns for
//    same relation only one column is req
    // @JoinColumn: user user for forign key relationship
//    user user_id as name for it
    @ManyToOne
    @JoinColumn(name = "user")
    private User user;
    private Date time;

//    1booking 1show
//    1show manybooking
    @ManyToOne
    private MovieShow movieShow;
    private double amount;

    @OneToMany
    private List<Payment> payments;

}
