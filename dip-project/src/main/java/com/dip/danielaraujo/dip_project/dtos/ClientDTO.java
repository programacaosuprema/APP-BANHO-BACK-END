package com.dip.danielaraujo.dip_project.dtos;

import com.dip.danielaraujo.dip_project.entities.AuthenticationEntity;
import com.dip.danielaraujo.dip_project.entities.ClientEntity;
import com.dip.danielaraujo.dip_project.entities.ImageEntity;

public record ClientDTO (Long id, String firstName, String lastName, String email, String phoneNumber, ImageDTO image, String password) {
    public ClientDTO(ClientEntity client) {
        this(client.getId(), client.getFirstName(), client.getLastName(), client.getEmail(),
                client.getPhoneNumber(), new ImageDTO(client.getImage()), client.getAuthentication().getPassword());
    }

    public static ClientDTO fromEntity(ClientEntity client) {
        return new ClientDTO(client);
    }
}
