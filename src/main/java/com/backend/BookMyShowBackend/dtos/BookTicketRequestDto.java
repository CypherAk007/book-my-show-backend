package com.backend.BookMyShowBackend.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookTicketRequestDto {
    //instead of getting whole object we get only ids from
    // front end and retrive details from db
    private List<Long> showSeatIds;
    private Long userId;
    private Long showId;

    public List<Long> getShowSeatIds() {
        return showSeatIds;
    }

    public Long getShowId(){
        return showId;
    }
}
