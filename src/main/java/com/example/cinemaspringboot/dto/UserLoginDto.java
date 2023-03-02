package com.example.cinemaspringboot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserLoginDto {

    @NotBlank(message = "Login is mandatory")
    @Size(min = 6, max = 32)
    private String login;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 6, max = 32)
    private String password;
}
