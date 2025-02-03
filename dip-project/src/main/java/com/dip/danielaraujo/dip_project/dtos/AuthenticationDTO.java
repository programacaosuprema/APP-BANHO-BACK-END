package com.dip.danielaraujo.dip_project.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AuthenticationDTO(
        @NotBlank(message = "O email é obrigatório")
        @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "Email inválido")
        String login,
        @NotBlank(message = "A senha é obrigatória")
        String password) {}
