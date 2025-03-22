package com.backend.BookMyShowBackend.controllers;

import com.backend.BookMyShowBackend.dtos.BookTicketRequestDto;
import com.backend.BookMyShowBackend.dtos.BookTicketResponseDto;
import com.backend.BookMyShowBackend.dtos.ResponseStatus;
import com.backend.BookMyShowBackend.models.Booking;
import com.backend.BookMyShowBackend.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }

    public BookTicketResponseDto bookTicket(BookTicketRequestDto requestDto){
        BookTicketResponseDto responseDto = new BookTicketResponseDto();
        try{
            Booking booking = bookingService.bookMovie(requestDto.getShowSeatIds(),requestDto.getShowId(),requestDto.getUserId());
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
            responseDto.setBookingId(booking.getId());
            responseDto.setAmount(booking.getAmount());
        }catch (Exception e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }
}
