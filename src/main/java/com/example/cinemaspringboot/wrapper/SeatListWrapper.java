package com.example.cinemaspringboot.wrapper;

import com.example.cinemaspringboot.database.entity.Seat;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class SeatListWrapper  {

    private List<Seat> seats;

    public SeatListWrapper(List<Seat> seats) {
        this.seats = seats;
    }

    public boolean contains(Integer row, Integer col) {
        return seats.contains(Seat.builder()
                .row(row)
                .col(col)
                .build());
    }
}
