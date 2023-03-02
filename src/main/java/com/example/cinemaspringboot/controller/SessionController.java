package com.example.cinemaspringboot.controller;

import com.example.cinemaspringboot.database.repository.FilmRepository;
import com.example.cinemaspringboot.database.repository.SessionRepository;
import com.example.cinemaspringboot.dto.SessionDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
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
        sessionRepository.save(sessionDto.convertToEntity());
        return "redirect:/sessions";
    }


}
