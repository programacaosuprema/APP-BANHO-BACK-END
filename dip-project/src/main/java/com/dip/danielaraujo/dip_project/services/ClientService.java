package com.dip.danielaraujo.dip_project.services;

import com.dip.danielaraujo.dip_project.Exceptions.EmptyStringException;
import com.dip.danielaraujo.dip_project.dtos.ClientDTO;
import com.dip.danielaraujo.dip_project.entities.ClientEntity;
import com.dip.danielaraujo.dip_project.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public ClientDTO create(ClientDTO clientDTO){

        this.validateData(clientDTO);

        ClientEntity clientEntity = new ClientEntity(clientDTO);

        return new ClientDTO(clientRepository.save(clientEntity));
    }

    public ClientDTO findByName(String name){
        String str = "cannot be empty.";
        if (name.isBlank()){
            throw new EmptyStringException("The first name " + str);
        }

        Optional<ClientEntity> client = this.clientRepository.findClientByFirstName(name);

        return client.map(ClientDTO::new).orElse(null);
    }

    public List<ClientDTO> findAll(){
        return this.clientRepository.findAll().stream().map(ClientDTO::fromEntity).toList();
    }

    public void validateData(ClientDTO data){
        String str = "cannot be empty.";
        if (data.firstName().isBlank()){
            throw new EmptyStringException("The first name " + str);
        }else if (data.email().isBlank()){
            throw new EmptyStringException("The e-mail " + str);
        }else if (data.phoneNumber().isBlank()){
            throw new EmptyStringException("The phone number " + str);
        }else if (data.password().isBlank()){
            throw new EmptyStringException("The password " + str);
        }
    }
}
