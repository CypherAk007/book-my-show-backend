package com.backend.BookMyShowBackend.services;

import com.backend.BookMyShowBackend.models.*;
import com.backend.BookMyShowBackend.repositories.BookingRepository;
import com.backend.BookMyShowBackend.repositories.ShowRepository;
import com.backend.BookMyShowBackend.repositories.ShowSeatRepository;
import com.backend.BookMyShowBackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final UserRepository userRepository;
    private final ShowRepository showRepository;
    private final ShowSeatRepository showSeatRepository;
    private final BookingRepository bookingRepository;
    private final PriceCalculatorService priceCalculatorService;


    @Autowired
    public BookingService(UserRepository userRepository, ShowRepository showRepository, ShowSeatRepository showSeatRepository, BookingRepository bookingRepository,PriceCalculatorService priceCalculatorService) {
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.bookingRepository = bookingRepository;
        this.priceCalculatorService = priceCalculatorService;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
//     This will be running DB lock - open transaction in serializable isolation - do the transaction - close the transaction
    public Booking bookMovie(List<Long> showSeatIds, Long showId, Long userId) {
//        Optional<User> userOptional = userRepository.findById(userId);
//        User user = userOptional.get();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User with given Id not found"));

        MovieShow movieShow = showRepository.findById(showId)
                .orElseThrow(() -> new RuntimeException("Show with given ShowId not present"));

        //        How to find all entities by list of ids
        //        step3
        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds);

        boolean allAvailable = showSeats.stream()
                .allMatch(this::isShowSeatAvailable);

        if(!allAvailable){
            throw new RuntimeException("Selected Seats are not available!!");
        }

        showSeats.stream()
                .forEach(showSeat -> {

                    showSeat.setShowSeatStatus(ShowSeatStatus.LOCKED);
                    showSeat.setBlockedAt(new Date());
                });

        List<ShowSeat> savedShowSeats = showSeatRepository.saveAll(showSeats);

        Booking booking = Booking.builder()
                .movieShow(movieShow)
                .showSeats(savedShowSeats)
                .user(user)
                .status(BookingStatus.PENDING)
                .time(new Date())
                .amount(priceCalculatorService.calculatePrice(movieShow,savedShowSeats))
                .payments(new ArrayList<>())
                .build();

        Booking savedBooking = bookingRepository.save(booking);
        return savedBooking;
    }

    private boolean isShowSeatAvailable(ShowSeat showSeat) {
        if ((showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)) || showSeat.getShowSeatStatus().equals(ShowSeatStatus.LOCKED) &&
                Duration.between(new Date().toInstant(), showSeat.getBlockedAt().toInstant()).toMinutes() > 15) {
            return true;
        }
        return false;

    }
}


//        --------------------------- For Today: Take Lock ---------------------------------
//            1. Get the user from userId
//            2. Get the show from showId
//        -------------------------------Take the Lock -------------------------
//        3. Get Show Seats from showSeatIds
//        4.Check all the seates are available or not
//        5. If not, throw err
//        6. If yes, mark the status for these seates as LOCKED
//        7. Save the updated information in the database
//        ------------------ Release Lock -------------------
//        8. create corresponding booking obj
            // Save the booking as booking id should be generated and we have
            // never saved booking information in the database
//        9. Return the booking object
//        --------------------------- For Today: Release Lock ---------------------------------