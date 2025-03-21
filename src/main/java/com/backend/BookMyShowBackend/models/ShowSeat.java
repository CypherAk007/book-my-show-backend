package com.backend.BookMyShowBackend.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ShowSeat extends BaseModel{
    private MovieShow movieShow;
    private Seat seat;
    private ShowSeatStatus showSeatStatus;
    private Date blockedAt;
}
