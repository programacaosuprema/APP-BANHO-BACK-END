package com.dip.danielaraujo.dip_project.services;

import com.dip.danielaraujo.dip_project.dtos.ClientDTO;
import com.dip.danielaraujo.dip_project.entities.ClientEntity;

import java.util.ArrayList;
import java.util.List;

public class ClientService {
    List<ClientEntity> clients = new ArrayList<>();
    public List<ClientEntity> create(ClientDTO clientDTO){
        ClientEntity entity = ClientDTO.toEntity(clientDTO);
        clients.add(entity);
        return clients;
    }
}
