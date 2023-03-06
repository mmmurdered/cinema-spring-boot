package com.example.cinemaspringboot.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;

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
