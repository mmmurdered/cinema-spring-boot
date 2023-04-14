package com.example.cinemaspringboot.controller;

import com.example.cinemaspringboot.database.entity.Session;
import com.example.cinemaspringboot.database.repository.FilmRepository;
import com.example.cinemaspringboot.database.repository.SessionRepository;
import com.example.cinemaspringboot.dto.SessionDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/session")
public class SessionController {

    private final SessionRepository sessionRepository;

    private final FilmRepository filmRepository;

    public SessionController(SessionRepository sessionRepository, FilmRepository filmRepository) {
        this.sessionRepository = sessionRepository;
        this.filmRepository = filmRepository;
    }

    @GetMapping("/all")
    public String getAllSessions(Model model) {
        model.addAttribute("cinema_sessions", sessionRepository.findAll());
        return "session/sessions";
    }

    @GetMapping("/all/{time}")
    public String getAllSessionsFilter(@PathVariable("time") String time, Model model) {
        LocalDateTime today = LocalDateTime.now();
        List<Session> filteredSessions = sessionRepository.findAll();
        switch (time) {
            case "today":
                filteredSessions = sessionRepository.findAllByTimeIsBetween(today, today.plusDays(1));
                break;
            case "tomorrow":
                filteredSessions = sessionRepository.findAllByTimeIsBetween(today.plusDays(1), today.plusDays(2));
                break;
            case "week":
                filteredSessions = sessionRepository.findAllByTimeIsBetween(today, today.plusDays(7));
                break;
        }
        model.addAttribute("cinema_sessions", filteredSessions);
        return "session/sessions";
    }

    @GetMapping("/add")
    public String saveSessionForm(SessionDto sessionDto, Model model) {
        model.addAttribute("films", filmRepository.findAll());
        return "session/add-session";
    }

    @PostMapping("/add")
    public String saveSession(@Valid SessionDto sessionDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "session/add-session";
        }
        sessionRepository.save(Session.builder()
                .time(sessionDto.getTime())
                .film(sessionDto.getFilm())
                .availablePlaces(sessionDto.getAvailablePlaces())
                .price(sessionDto.getPrice())
                .build());
        return "redirect:/session/all";
    }

    @GetMapping("/update/{id}")
    public String updateSession(@PathVariable("id") int id, Model model) {
        model.addAttribute("films", filmRepository.findAll());
        Session session = sessionRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Invalid session id"));
        model.addAttribute("sessionDto", session);
        return "session/edit-session";
    }

    @PostMapping("/update/{id}")
    public String updateSession(@PathVariable("id") int id, @Valid SessionDto sessionDto, BindingResult bindingResult,
                                Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("films", filmRepository.findAll());
            return "session/edit-session";
        }
        sessionRepository.save(Session.builder()
                .id(id)
                .time(sessionDto.getTime())
                .price(sessionDto.getPrice())
                .film(sessionDto.getFilm())
                .availablePlaces(sessionDto.getAvailablePlaces())
                .build());
        return "redirect:/session/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteSession(@PathVariable("id") int id) {
        Session session = sessionRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Invalid session id"));
        sessionRepository.delete(session);
        return "redirect:/session/all";
    }
}
