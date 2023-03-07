package com.example.cinemaspringboot.database.repository;

import com.example.cinemaspringboot.database.entity.Session;
import com.example.cinemaspringboot.database.entity.Ticket;
import com.example.cinemaspringboot.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    List<Ticket> findAllBySession(Session session);

    List<Ticket> findAllBySessionAndUser(Session session, User user);

    List<Ticket> findAllByUser(User user);
}
