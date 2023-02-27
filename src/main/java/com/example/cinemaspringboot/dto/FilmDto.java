package com.example.cinemaspringboot.dto;

import com.example.cinemaspringboot.database.entity.Film;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class FilmDto {

    @NotBlank(message = "Title is mandatory(1 - 100)")
    @Size(min = 1, max = 100)
    private String title;

    @NotBlank(message = "Description is mandatory(1 - 2000)")
    @Size(min = 1, max = 2000)
    private String description;

    @NotBlank(message = "Title is mandatory(1 - 45)")
    @Size(min = 1, max = 45)
    private String genre;

    @Positive
    private Integer duration;

    @Positive
    @Range(min = 0, max = 10)
    private Double imdbRating;

    public Film convertToEntity(){
        return new Film(null , title, description, genre, duration, imdbRating);
    }
}
