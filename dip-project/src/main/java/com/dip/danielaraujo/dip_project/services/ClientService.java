package com.dip.danielaraujo.dip_project.services;

import com.dip.danielaraujo.dip_project.entities.AuthenticationEntity;
import com.dip.danielaraujo.dip_project.entities.ImageEntity;
import com.dip.danielaraujo.dip_project.exceptions.InvalidDataFromClientException;
import com.dip.danielaraujo.dip_project.dtos.ClientDTO;
import com.dip.danielaraujo.dip_project.entities.ClientEntity;
import com.dip.danielaraujo.dip_project.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ValidationService validate;

    public ClientDTO findById(Long id){
        return new ClientDTO(this.clientRepository.findById(id).orElseThrow(() -> new ExpressionException("Client not found")));
    }

    public ClientDTO create(ClientDTO clientDTO){

        this.validate.validateClient(clientDTO);

        ClientEntity clientEntity = new ClientEntity(clientDTO);

        return new ClientDTO(clientRepository.save(clientEntity));
    }

    public ClientDTO findByName(String name){
        String str = "cannot be empty.";
        if (name.isBlank()){
            throw new InvalidDataFromClientException("The first name " + str);
        }

        Optional<ClientEntity> client = this.clientRepository.findClientByFirstName(name);

        return client.map(ClientDTO::new).orElse(null);
    }

    public List<ClientDTO> findAll(){
        return this.clientRepository.findAll().stream().map(ClientDTO::fromEntity).toList();
    }

    public ClientDTO update(Long id, ClientDTO clientDTO){
        ClientEntity client = new ClientEntity(this.findById(id));

        this.validate.validateClient(clientDTO);

        client.setFirstName(clientDTO.firstName());
        client.setLastName(clientDTO.lastName());
        client.setEmail(clientDTO.email());
        client.setPhoneNumber(clientDTO.phoneNumber());

        client.setAuthentication(new AuthenticationEntity(clientDTO.email(), clientDTO.password(), client));

        if (clientDTO.image() != null) {
            client.setImage(new ImageEntity(clientDTO.image().id(), clientDTO.image().name(), clientDTO.image().src(), client));
        } else {
            client.setImage(null);
        }

        return new ClientDTO(client);
    }
}
