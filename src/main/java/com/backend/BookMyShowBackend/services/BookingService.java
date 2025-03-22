package com.backend.BookMyShowBackend.services;

import com.backend.BookMyShowBackend.models.Booking;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookingService {

    @Transactional(isolation = Isolation.SERIALIZABLE)
//     This will be running DB lock - open transaction in serializable isolation - do the transaction - close the transaction
    public Booking bookMovie(List<Long> showSeatIds, Long showId, Long userId) {

        return null;
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