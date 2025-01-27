package com.dip.danielaraujo.dip_project.dtos;

import com.dip.danielaraujo.dip_project.entities.AuthenticationEntity;

import java.util.UUID;

public record AuthenticationDTO(String login, String password) {

    public AuthenticationDTO(AuthenticationEntity authentication) {
        this(authentication.getEmail(), authentication.getPassword());
    }

    public static AuthenticationDTO fromEntity(AuthenticationEntity authentication) {
        return new AuthenticationDTO(authentication);
    }
}
