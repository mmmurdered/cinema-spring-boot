package com.example.cinemaspringboot.dto;

import com.example.cinemaspringboot.database.entity.Film;
import com.example.cinemaspringboot.database.entity.Session;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;

@Data
public class SessionDto {

    @NotNull
    private LocalDateTime time;

    @Positive
    @Range(min = 1, max = 128)
    private Integer availablePlaces;

    @Positive
    private Integer price;

    @NotNull
    private Film film;

    public Session convertToEntity(){
        return new Session(null, time, availablePlaces, price, film);
    }
}
