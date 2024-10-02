package com.dip.danielaraujo.dip_project.dtos;

import com.dip.danielaraujo.dip_project.entities.AuthenticationEntity;
import com.dip.danielaraujo.dip_project.entities.ClientEntity;
import com.dip.danielaraujo.dip_project.entities.ImageEntity;

public record ClientDTO (Long id, String name, String surname, String email, String phoneNumber, ImageDTO image, String password) {
    public static ClientDTO fromEntity(ClientEntity client) {
        return new ClientDTO(
                client.getId(),
                client.getName(),
                client.getSurname(),
                client.getEmail(),
                client.getPhoneNumber(),
                client.getImage() != null ? ImageDTO.fromEntity(client.getImage()) : null,
                client.getAuthentication().getPassword()
        );
    }

    // Método para converter de ClientDTO para ClientEntity
    public static ClientEntity toEntity(ClientDTO clientDTO) {
        ClientEntity clientEntity = new ClientEntity();

        ImageDTO imageDTO = clientDTO.image;

        ImageEntity imageEntity = new ImageEntity(null, imageDTO.name(), imageDTO.src(), null);

        AuthenticationEntity authenticationEntity = new AuthenticationEntity(clientDTO.email, clientDTO.password, null);

        //implementação futura se precisar
        clientEntity.setId(null);
        clientEntity.setName(clientDTO.name());
        clientEntity.setSurname(clientDTO.surname());
        clientEntity.setEmail(clientDTO.email());
        clientEntity.setPhoneNumber(clientDTO.phoneNumber());

        clientEntity.setImage(imageEntity);
        clientEntity.setAuthentication(authenticationEntity);

        return clientEntity;
    }
}
