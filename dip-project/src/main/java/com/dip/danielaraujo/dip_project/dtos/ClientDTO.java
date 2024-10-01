package com.dip.danielaraujo.dip_project.dtos;

import com.dip.danielaraujo.dip_project.entities.ClientEntity;

public record ClientDTO (Long id, String name, String surname, String email, String phoneNumber) {
    public static ClientDTO fromEntity(ClientEntity client) {
        return new ClientDTO(
                client.getId(),
                client.getName(),
                client.getSurname(),
                client.getEmail(),
                client.getPhoneNumber()
        );
    }

    // Método para converter de ClientDTO para ClientEntity
    public static ClientEntity toEntity(ClientDTO clientDTO) {
        ClientEntity client = new ClientEntity();
        client.setId(clientDTO.id());
        client.setName(clientDTO.name());
        client.setSurname(clientDTO.surname());
        client.setEmail(clientDTO.email());
        client.setPhoneNumber(clientDTO.phoneNumber());
        return client;
    }
}
