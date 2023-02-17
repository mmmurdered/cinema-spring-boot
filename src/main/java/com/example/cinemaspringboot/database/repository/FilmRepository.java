package com.example.cinemaspringboot.database.repository;

import com.example.cinemaspringboot.database.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<Film, Integer> {
}
