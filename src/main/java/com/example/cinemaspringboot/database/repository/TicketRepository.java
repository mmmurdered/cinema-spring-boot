package com.example.cinemaspringboot.database.repository;

import com.example.cinemaspringboot.database.entity.Session;
import com.example.cinemaspringboot.database.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    List<Ticket> findAllBySession(Session session);
}
