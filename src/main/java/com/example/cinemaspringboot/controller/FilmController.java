package com.example.cinemaspringboot.controller;

import com.example.cinemaspringboot.database.repository.FilmRepository;
import com.example.cinemaspringboot.dto.FilmDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FilmController {

    @Autowired
    private FilmRepository filmRepository;

    @GetMapping("/add-film")
    public String saveFilmForm(FilmDto filmDto) {
        return "add-film";
    }

    @PostMapping("/add-film")
    public String saveFilm(@Valid FilmDto filmDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "film/add-film";
        }
        filmRepository.save(filmDto.convertToEntity());
        return "redirect:/films";
    }

    @GetMapping("/films")
    public String getAllFilms(Model model) {
        model.addAttribute("films", filmRepository.findAll());
        return "film/films";
    }


}
