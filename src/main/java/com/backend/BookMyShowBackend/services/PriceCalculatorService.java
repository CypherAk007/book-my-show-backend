package com.backend.BookMyShowBackend.services;

import com.backend.BookMyShowBackend.models.MovieShow;
import com.backend.BookMyShowBackend.models.ShowSeat;
import com.backend.BookMyShowBackend.models.ShowSeatType;
import com.backend.BookMyShowBackend.repositories.ShowSeatTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceCalculatorService {
    private ShowSeatTypeRepository showSeatTypeRepository;

    @Autowired
    public PriceCalculatorService(ShowSeatTypeRepository showSeatTypeRepository){
        this.showSeatTypeRepository = showSeatTypeRepository;
    }

    public double calculatePrice(MovieShow movieShow, List<ShowSeat> savedShowSeats){
        List<ShowSeatType> showSeatTypes = showSeatTypeRepository.findByMovieShow(movieShow);
        double amount = 0;
        for(ShowSeat showSeat: savedShowSeats){
            for(ShowSeatType showSeatType:showSeatTypes){
                if(showSeat.getSeat().getSeatType().equals(showSeatType.getSeatType())){
                    amount+=showSeatType.getPrice();
                }
            }
        }
        return amount;
    }
}
