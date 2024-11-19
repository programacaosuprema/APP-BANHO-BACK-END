package com.dip.danielaraujo.dip_project.dtos;

import com.dip.danielaraujo.dip_project.entities.ClientEntity;

import java.util.UUID;

public record ClientDTO (UUID id, String firstName, String lastName, String email, String phoneNumber, ImageClientDTO image, String password) {
    public ClientDTO(ClientEntity client) {
        this(client.getId(), client.getFirstName(), client.getLastName(), client.getEmail(),
                client.getPhoneNumber(), new ImageClientDTO(client.getImage()), client.getAuthentication().getPassword());
    }

    public static ClientDTO fromEntity(ClientEntity client) {
        return new ClientDTO(client);
    }
}
