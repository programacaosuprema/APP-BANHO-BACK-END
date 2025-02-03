package com.dip.danielaraujo.dip_project.dtos;

import com.dip.danielaraujo.dip_project.enums.UserRole;
import jakarta.validation.constraints.Pattern;

public record RegisterDTO(@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "Email inválido") String login, String password, UserRole role) {
}
