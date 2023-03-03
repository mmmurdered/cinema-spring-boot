package com.example.cinemaspringboot.dto;

import com.example.cinemaspringboot.database.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@RequiredArgsConstructor
public class UserDto {

    @NotBlank(message = "Login is mandatory")
    @Size(min = 6, max = 32)
    private String login;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 6, max = 32)
    private String password;

    @Email
    @NotBlank(message = "Email is mandatory")
    private String email;
}
