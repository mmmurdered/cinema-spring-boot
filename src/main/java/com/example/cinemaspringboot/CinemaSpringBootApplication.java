package com.example.cinemaspringboot;

import com.example.cinemaspringboot.database.entity.User;
import com.example.cinemaspringboot.database.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.reactive.ReactiveManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CinemaSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(CinemaSpringBootApplication.class, args);
    }

}
