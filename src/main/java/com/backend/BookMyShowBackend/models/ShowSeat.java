package com.backend.BookMyShowBackend.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class ShowSeat extends BaseModel{
    private MovieShow movieShow;
    private Seat seat;
    private ShowSeatStatus showSeatStatus;
    private Date blockedAt;
}
