package com.dip.danielaraujo.dip_project.dtos;

public record TokenDTO(String token) {
    public TokenDTO {
        if (token == null || token.isBlank()) {
            throw new RuntimeException("Token cannot be null or empty");
        }
    }
}
