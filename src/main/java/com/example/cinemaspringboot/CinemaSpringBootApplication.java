package com.example.cinemaspringboot;

import com.example.cinemaspringboot.database.entity.User;
import com.example.cinemaspringboot.database.entity.UserRole;
import com.example.cinemaspringboot.database.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Optional;

@SpringBootApplication
public class CinemaSpringBootApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CinemaSpringBootApplication.class, args);
        UserRepository bean = context.getBean(UserRepository.class);
        User user = bean.findById(1).get();
        System.out.println(user);
        /*UserRole userRole = new UserRole(1, "User");
        User user = new User(999, userRole, "dfdfg", "dgsfg", "dfgdfg");
        bean.save(user);*/
    }

}
