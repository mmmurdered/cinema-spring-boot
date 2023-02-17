package com.example.cinemaspringboot.database.repository;

import com.example.cinemaspringboot.database.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, Integer> {
}
