package com.dip.danielaraujo.dip_project.services;

import com.dip.danielaraujo.dip_project.dtos.ClientDTO;
import com.dip.danielaraujo.dip_project.entities.ClientEntity;

import java.util.ArrayList;
import java.util.List;

public class ClientService {
    List<ClientDTO> clients = new ArrayList<>();
    public List<ClientDTO> create(ClientEntity entity){
        ClientDTO clientDTO = ClientDTO.fromEntity(entity);
        clients.add(clientDTO);
        return clients;
    }
}
