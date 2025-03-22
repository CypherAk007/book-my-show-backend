package com.backend.BookMyShowBackend.service;

import com.backend.BookMyShowBackend.models.*;
import com.backend.BookMyShowBackend.repositories.BookingRepository;
import com.backend.BookMyShowBackend.repositories.ShowRepository;
import com.backend.BookMyShowBackend.repositories.ShowSeatRepository;
import com.backend.BookMyShowBackend.repositories.UserRepository;
import com.backend.BookMyShowBackend.services.BookingService;
import com.backend.BookMyShowBackend.services.PriceCalculatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookingServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ShowRepository showRepository;

    @Mock
    private ShowSeatRepository showSeatRepository;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private PriceCalculatorService priceCalculatorService;

    @InjectMocks
    private BookingService bookingService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBookMovie_Success(){

        // Arrange
        Long userId = 1L;
        Long showId = 100L;
        List<Long> seatIds = Arrays.asList(10L, 20L);

        User mockUser = new User();
        mockUser.setId(userId);

        MovieShow mockMovieShow = new MovieShow();
        mockMovieShow.setId(showId);

        ShowSeat seat1 = new ShowSeat();
        seat1.setId(10L);
        seat1.setShowSeatStatus(ShowSeatStatus.AVAILABLE);

        ShowSeat seat2 = new ShowSeat();
        seat2.setId(20L);
        seat2.setShowSeatStatus(ShowSeatStatus.AVAILABLE);

        List<ShowSeat> mockSeats = Arrays.asList(seat1,seat2);

        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));
        when(showRepository.findById(showId)).thenReturn(Optional.of(mockMovieShow));
        when(showSeatRepository.findAllById(seatIds)).thenReturn(mockSeats);
        when(showSeatRepository.saveAll(anyList())).thenReturn(mockSeats);
        when(priceCalculatorService.calculatePrice(mockMovieShow,mockSeats)).thenReturn(1000.00);
        when(bookingRepository.save(any(Booking.class))).thenAnswer(invocationOnMock -> {
            Booking booking = invocationOnMock.getArgument(0);
            booking.setId(999L);
            return booking;
        });

//        Act
        Booking booking = bookingService.bookMovie(seatIds,showId,userId);

//        Assert
        assertNotNull(booking);
        assertEquals(999L,booking.getId());
        assertEquals(1000.00,booking.getAmount());
        assertEquals(BookingStatus.PENDING,booking.getStatus());
        assertEquals(mockUser,booking.getUser());
        assertEquals(mockMovieShow,booking.getMovieShow());
        assertEquals(2,booking.getShowSeats().size());

//        Verify
        verify(userRepository,times(1)).findById(userId);
        verify(showRepository,times(1)).findById(showId);
        verify(showSeatRepository,times(1)).findAllById(seatIds);
        verify(showSeatRepository,times(1)).saveAll(mockSeats);
        verify(priceCalculatorService,times(1)).calculatePrice(mockMovieShow,mockSeats);
        verify(bookingRepository,times(1)).save(any(Booking.class));
        // Print output
        System.out.println("✅ Booking Success: ID = " + booking.getId() + ", Amount = " + booking.getAmount());
    }

    @Test
    void testBookMovie_Failure_UserNotFound() {

        // Arrange
        Long userId = 1L;
        Long showId = 100L;
        List<Long> seatIds = Arrays.asList(10L, 20L);

        User mockUser = new User();
        mockUser.setId(userId);

        MovieShow mockMovieShow = new MovieShow();
        mockMovieShow.setId(showId);

        ShowSeat seat1 = new ShowSeat();
        seat1.setId(10L);
        seat1.setShowSeatStatus(ShowSeatStatus.AVAILABLE);

        ShowSeat seat2 = new ShowSeat();
        seat2.setId(20L);
        seat2.setShowSeatStatus(ShowSeatStatus.AVAILABLE);

        List<ShowSeat> mockSeats = Arrays.asList(seat1, seat2);

        when(userRepository.findById(userId)).thenReturn(Optional.empty());
//        ()-> lazy Loading
        Exception exception = assertThrows(RuntimeException.class, () -> bookingService.bookMovie(seatIds, showId, userId));
        assertEquals("User with given Id not found", exception.getMessage());

        verify(userRepository, times(1)).findById(userId);
        verifyNoMoreInteractions(showRepository, showSeatRepository, priceCalculatorService, bookingRepository);

        // Print output
        System.out.println("✅ Booking Failure: " + exception.getMessage());

    }

    @Test
    void testBookMovie_Failure_SeatNotAvailable() {

        // Arrange
        Long userId = 1L;
        Long showId = 100L;
        List<Long> seatIds = Arrays.asList(10L, 20L);

        User mockUser = new User();
        mockUser.setId(userId);

        MovieShow mockMovieShow = new MovieShow();
        mockMovieShow.setId(showId);

        ShowSeat seat1 = new ShowSeat();
        seat1.setId(10L);
        seat1.setShowSeatStatus(ShowSeatStatus.OCCUPIED);

        ShowSeat seat2 = new ShowSeat();
        seat2.setId(20L);
        seat2.setShowSeatStatus(ShowSeatStatus.AVAILABLE);

        List<ShowSeat> mockSeats = Arrays.asList(seat1, seat2);

        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));
        when(showRepository.findById(showId)).thenReturn(Optional.of(mockMovieShow));
        when(showSeatRepository.findAllById(seatIds)).thenReturn(mockSeats);

        Exception exception = assertThrows(RuntimeException.class, () -> bookingService.bookMovie(seatIds, showId, userId));
        assertEquals("Selected Seats are not available!!", exception.getMessage());

        verify(userRepository, times(1)).findById(userId);
        verify(showRepository, times(1)).findById(showId);
        verify(showSeatRepository, times(1)).findAllById(seatIds);

        verifyNoMoreInteractions(priceCalculatorService, bookingRepository);

        // Print output
        System.out.println("✅ Booking Failure: " + exception.getMessage());
    }

}
