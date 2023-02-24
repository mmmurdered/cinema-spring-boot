package com.example.cinemaspringboot.controller;

import com.example.cinemaspringboot.database.entity.Film;
import com.example.cinemaspringboot.database.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FilmController {

    @Autowired
    private FilmRepository filmRepository;

    @GetMapping("/add-film")
    public String saveFilmForm(Film film){
        return "add-film";
    }

    @PostMapping("/add-film")
    public String saveFilm(Film film){
        filmRepository.save(film);
        return "redirect:/films";
    }

    @GetMapping("/films")
    public String getAllFilms(Model model){
        model.addAttribute("films", filmRepository.findAll());
        return "films";
    }


}
