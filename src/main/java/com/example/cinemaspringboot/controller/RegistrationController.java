package com.example.cinemaspringboot.controller;

import com.example.cinemaspringboot.database.entity.User;
import com.example.cinemaspringboot.database.entity.UserRole;
import com.example.cinemaspringboot.database.repository.UserRepository;
import com.example.cinemaspringboot.dto.UserDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/registration")
    public String saveUser(UserDto userDto, Model model) {
        return "user/registration";
    }

    @PostMapping("/registration")
    public String saveUser(@Valid UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user/registration";
        }
        userRepository.save(User.builder()
                .login(userDto.getLogin())
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .role(new UserRole(1, "User"))
                .build());
        return "redirect:/sessions";
    }

}
