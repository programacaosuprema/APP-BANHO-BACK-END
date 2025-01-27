package com.dip.danielaraujo.dip_project.dtos;

import com.dip.danielaraujo.dip_project.enums.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
