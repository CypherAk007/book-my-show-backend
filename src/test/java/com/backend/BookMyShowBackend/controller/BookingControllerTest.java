package com.backend.BookMyShowBackend.controller;

import com.backend.BookMyShowBackend.controllers.BookingController;
import com.backend.BookMyShowBackend.dtos.BookTicketRequestDto;
import com.backend.BookMyShowBackend.dtos.BookTicketResponseDto;
import com.backend.BookMyShowBackend.dtos.ResponseStatus;
import com.backend.BookMyShowBackend.models.Booking;
import com.backend.BookMyShowBackend.services.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

public class BookingControllerTest {

    @Mock
    private BookingService bookingService;

    @InjectMocks
    private BookingController bookingController;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);// ✅ Initializes mocks before each test
    }

    @Test
    void testBookTicket_success(){
        List<Long> showSeatIds = Arrays.asList(1L,2L);
        Long showId = 100L;
        Long userId = 500L;

        BookTicketRequestDto bookTicketRequestDto= new BookTicketRequestDto();
        bookTicketRequestDto.setShowId(showId);
        bookTicketRequestDto.setUserId(userId);
        bookTicketRequestDto.setShowSeatIds(showSeatIds);

        Booking mockBooking = new Booking();
        mockBooking.setId(123L);
        mockBooking.setAmount(1000.00);
        mockBooking.setTime(new Date());

        when(bookingService.bookMovie(showSeatIds,showId,userId)).thenReturn(mockBooking);

        BookTicketResponseDto responseDto = bookingController.bookTicket(bookTicketRequestDto);

        assertEquals(ResponseStatus.SUCCESS,responseDto.getResponseStatus());
        assertEquals(123L,responseDto.getBookingId());
        assertEquals(1000,responseDto.getAmount());

        System.out.println("Booking Created: ID = "+responseDto.getBookingId()+", Amount = "+responseDto.getAmount()+" , Transaction Status : "+responseDto.getResponseStatus());

    }

    @Test
    void testBookTicket_failure(){
        List<Long> showSeatIds = Arrays.asList(1L,2L);
        Long showId = 100L;
        Long userId = 500L;

        BookTicketRequestDto bookTicketRequestDto= new BookTicketRequestDto();
        bookTicketRequestDto.setShowId(showId);
        bookTicketRequestDto.setUserId(userId);
        bookTicketRequestDto.setShowSeatIds(showSeatIds);

        when(bookingService.bookMovie(anyList(),any(),any()))
                .thenThrow(new RuntimeException("Seats not available"));

        BookTicketResponseDto responseDto = bookingController.bookTicket(bookTicketRequestDto);

        assertEquals(ResponseStatus.FAILURE,responseDto.getResponseStatus());

        System.out.println("Booking Failed : Status = "+responseDto.getResponseStatus());
    }




}
