package com.example.cinemaspringboot.database.repository;

import com.example.cinemaspringboot.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserByLoginAndPassword(String login, String password);

    User findUserByLogin(String login);
}
