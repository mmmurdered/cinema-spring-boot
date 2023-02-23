package com.example.cinemaspringboot.controller;

import com.example.cinemaspringboot.database.entity.Session;
import com.example.cinemaspringboot.database.repository.FilmRepository;
import com.example.cinemaspringboot.database.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class SessionController {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private FilmRepository filmRepository;

    @GetMapping("/sessions")
    public String getAllSessions(Model model){
        model.addAttribute("cinema_sessions", sessionRepository.findAll());
        return "sessions";
    }

    @GetMapping("add-session")
    public String saveSessionForm(Session session, Model model){
        model.addAttribute("films", filmRepository.findAll());
        return "add-session";
    }

    @PostMapping("/add-session")
    public String saveSession(Session session){
        sessionRepository.save(session);
        return "redirect:/sessions";
    }


}
