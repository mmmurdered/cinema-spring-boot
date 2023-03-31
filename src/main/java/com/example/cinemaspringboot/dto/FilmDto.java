package com.example.cinemaspringboot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class FilmDto {

    @NotNull
    @Positive
    private Integer id;

    @NotBlank(message = "Title is mandatory(1 - 100)")
    @Size(min = 1, max = 100)
    private String title;

    @NotBlank(message = "Description is mandatory(1 - 2000)")
    @Size(min = 1, max = 2000)
    private String description;

    @NotBlank(message = "Title is mandatory(1 - 45)")
    @Size(min = 1, max = 45)
    private String genre;

    @NotNull
    @Positive
    private Integer duration;

    @NotNull
    @Positive
    @Range(min = 1, max = 3, message = "Invalid rating format")
    private Double imdbRating;

    @Size(min = 5, max = 1000)
    private String posterLink;
}
