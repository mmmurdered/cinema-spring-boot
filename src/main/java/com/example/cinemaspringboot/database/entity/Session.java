package com.example.cinemaspringboot.database.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //@DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm")
    @Column(nullable = false)
    private LocalDateTime time;

    @Column
    private Integer availablePlaces;

    @Column(nullable = false)
    private Integer price;

    @ManyToOne
    private Film film;

}



