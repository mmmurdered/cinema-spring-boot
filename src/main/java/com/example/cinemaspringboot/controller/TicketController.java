package com.example.cinemaspringboot.controller;

import com.example.cinemaspringboot.database.entity.Seat;
import com.example.cinemaspringboot.database.entity.Session;
import com.example.cinemaspringboot.database.entity.Ticket;
import com.example.cinemaspringboot.database.entity.User;
import com.example.cinemaspringboot.database.repository.SeatRepository;
import com.example.cinemaspringboot.database.repository.SessionRepository;
import com.example.cinemaspringboot.database.repository.TicketRepository;
import com.example.cinemaspringboot.database.repository.UserRepository;
import com.example.cinemaspringboot.wrapper.SeatListWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping("/ticket")
public class TicketController {

    private final SessionRepository sessionRepository;

    private final SeatRepository seatRepository;

    private final TicketRepository ticketRepository;

    private final UserRepository userRepository;

    public TicketController(SessionRepository sessionRepository, SeatRepository seatRepository,
                            TicketRepository ticketRepository, UserRepository userRepository) {
        this.sessionRepository = sessionRepository;
        this.seatRepository = seatRepository;
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/buy/{id}")
    public String buyTicket(@PathVariable("id") int id, Model model) {
        model.addAttribute("cinemaSession", sessionRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Illegal session id")));
        List<Ticket> tickets = ticketRepository.findAllBySession(sessionRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Illegal session id")));
        List<Seat> seats = new ArrayList<>();
        tickets.forEach(t -> seats.add(t.getSeat()));
        model.addAttribute("seats", new SeatListWrapper(seats));
        return "ticket/buy-ticket";
    }

    @PostMapping("/buy/{id}")
    public String buyTicket(@PathVariable("id") int id, @RequestParam("seat") List<String> values,
                            Authentication authentication) {
        Session session = sessionRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Illegal session id"));
        User user = userRepository.findUserByLogin(authentication.getName()).orElseThrow(
                () -> new IllegalArgumentException("Invalid user id"));

        List<Seat> allSeats = new ArrayList<>();
        values.forEach(str -> {
            Seat seat = Seat.builder()
                    .row(Integer.parseInt(str.substring(0, 1)))
                    .col(Integer.parseInt(str.substring(2, 3)))
                    .build();
            seatRepository.save(seat);
            allSeats.add(seat);
        });
        allSeats.forEach(seat -> ticketRepository.save(Ticket.builder()
                .film(session.getFilm())
                .seat(seat)
                .user(user)
                .session(session)
                .build()));
        return "redirect:/session/sessions";
    }

    @GetMapping("/seats/{id}")
    public String showSeats(@PathVariable("id") int id, Model model, Authentication authentication) {
        Session session = sessionRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Illegal session id"));
        User user = userRepository.findUserByLogin(authentication.getName()).orElseThrow(
                () -> new IllegalArgumentException("Invalid user id"));
        List<Ticket> tickets = ticketRepository.findAllBySessionAndUser(session, user);
        List<Seat> seats = new ArrayList<>();
        tickets.forEach(t -> seats.add(t.getSeat()));
        model.addAttribute("seats", new SeatListWrapper(seats));
        return "ticket/show-seats";
    }

    @GetMapping("/tickets")
    public String showTicketsToUser(Model model, Authentication authentication) {
        List<Ticket> tickets = ticketRepository.findAllByUser(
                userRepository.findUserByLogin(authentication.getName()).orElseThrow(
                        () -> new IllegalArgumentException("Invalid username")
                ));

        model.addAttribute("tickets", new HashSet<>(tickets));
        return "ticket/tickets";
    }
}
