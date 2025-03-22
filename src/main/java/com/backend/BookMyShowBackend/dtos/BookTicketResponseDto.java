package com.backend.BookMyShowBackend.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookTicketResponseDto {
    private ResponseStatus responseStatus;
    private Long bookingId;

//    can be fetched from booking class via booking id
//    but to save from one join we introduce redudancy
    private double amount;
}
