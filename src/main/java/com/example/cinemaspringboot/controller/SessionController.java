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

@Controller
@RequestMapping("/session")
public class SessionController {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private FilmRepository filmRepository;

    @GetMapping("/sessions")
    public String getAllSessions(Model model) {
        model.addAttribute("cinema_sessions", sessionRepository.findAll());
        return "session/sessions";
    }

    @GetMapping("/add-session")
    public String saveSessionForm(SessionDto sessionDto, Model model) {
        model.addAttribute("films", filmRepository.findAll());
        return "session/add-session";
    }

    @PostMapping("/add-session")
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
        return "redirect:/sessions";
    }

    @GetMapping("/update/{id}")
    public String updateSession(@PathVariable("id") int id, Model model){
        model.addAttribute("films", filmRepository.findAll());
        Session session = sessionRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Invalid session id"));
        model.addAttribute("sessionDto", session);
        return "session/edit-session";
    }

    @PostMapping("/update/{id}")
    public String updateSession(@PathVariable("id") int id, @Valid SessionDto sessionDto, BindingResult bindingResult,
                                Model model){
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
        return "redirect:/sessions";
    }

    @GetMapping("/delete/{id}")
    public String deleteSession(@PathVariable("id") int id){
        Session session = sessionRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Invalid session id"));
        sessionRepository.delete(session);
        return "redirect:/sessions";
    }
}
