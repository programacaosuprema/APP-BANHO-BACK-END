package com.dip.danielaraujo.dip_project.dtos;

import com.dip.danielaraujo.dip_project.entities.AuthenticationEntity;

import java.util.UUID;

public record AuthenticationDTO(UUID id, String email, String password, UUID client_id) {

    public AuthenticationDTO(AuthenticationEntity authentication) {
        this(authentication.getId(), authentication.getEmail(), authentication.getPassword(), authentication.getClient().getId());
    }

    public static AuthenticationDTO fromEntity(AuthenticationEntity authentication) {
        return new AuthenticationDTO(authentication);
    }
}
