package com.example.cinemaspringboot.controller;

import com.example.cinemaspringboot.database.entity.Film;
import com.example.cinemaspringboot.database.repository.FilmRepository;
import com.example.cinemaspringboot.dto.FilmDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/film")
public class FilmController {

    private final FilmRepository filmRepository;

    public FilmController(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @GetMapping("/add")
    public String saveFilmForm(FilmDto filmDto) {
        return "film/add-film";
    }

    @PostMapping("/add")
    public String saveFilm(@Valid FilmDto filmDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "film/add-film";
        }
        filmRepository.save(Film.builder()
                .title(filmDto.getTitle())
                .description(filmDto.getDescription())
                .genre(filmDto.getGenre())
                .duration(filmDto.getDuration())
                .imdbRating(filmDto.getImdbRating())
                .posterLink(filmDto.getPosterLink())
                .build());
        return "redirect:/films";
    }

    @GetMapping("/all")
    public String getAllFilms(Model model) {
        model.addAttribute("films", filmRepository.findAll());
        return "film/films";
    }

    @GetMapping("/update/{id}")
    public String updateFilm(@PathVariable("id") int id, Model model) {
        Film film = filmRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Invalid film id"));
        model.addAttribute("filmDto", film);
        return "film/edit-film";
    }

    @PostMapping("/update/{id}")
    public String updateFilm(@PathVariable("id") int id, @Valid FilmDto filmDto,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("film", filmDto);
            return "film/edit-film";
        }
        filmRepository.save(Film.builder()
                .id(id)
                .title(filmDto.getTitle())
                .description(filmDto.getDescription())
                .genre(filmDto.getGenre())
                .duration(filmDto.getDuration())
                .imdbRating(filmDto.getImdbRating())
                .posterLink(filmDto.getPosterLink())
                .build());
        return "redirect:/films";
    }

    @GetMapping("/delete/{id}")
    public String deleteFilm(@PathVariable("id") int id) {
        Film film = filmRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Invalid film id"));
        filmRepository.delete(film);
        return "redirect:/films";
    }

    @GetMapping("/{id}")
    public String getFilm(@PathVariable("id") int id, Model model) {
        Film film = filmRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Invalid film id"));
        model.addAttribute("film", film);
        return "film/film-about";
    }
}
