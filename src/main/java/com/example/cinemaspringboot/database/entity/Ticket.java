package com.example.cinemaspringboot.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Session session;

    @ManyToOne
    @JoinColumn(name = "session_film_id")
    private Film film;

    @OneToOne
    private Seat seat;


    //TODO REFACTORING
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(user, ticket.user) && Objects.equals(session, ticket.session) && Objects.equals(film, ticket.film);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, session, film);
    }
}
