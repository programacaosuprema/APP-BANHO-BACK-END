package com.dip.danielaraujo.dip_project.services;

import com.dip.danielaraujo.dip_project.entities.AuthenticationEntity;
import com.dip.danielaraujo.dip_project.entities.ImageClientEntity;
import com.dip.danielaraujo.dip_project.entities.ImageDipEntity;
import com.dip.danielaraujo.dip_project.exceptions.ClientNotFoundException;
import com.dip.danielaraujo.dip_project.exceptions.InvalidDataException;
import com.dip.danielaraujo.dip_project.dtos.ClientDTO;
import com.dip.danielaraujo.dip_project.entities.ClientEntity;
import com.dip.danielaraujo.dip_project.repositories.ClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;;
import java.util.List;
import java.util.UUID;

import com.dip.danielaraujo.dip_project.repositories.AutheticationRepository;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ValidationService validate;

    @Autowired
    private AutheticationRepository authentication;

    public ClientDTO findById(UUID id){
        return new ClientDTO(this.clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException("Client not found")));
    }

    public ClientDTO create(ClientDTO clientDTO){

        this.validate = new ValidationService(clientDTO);

        if(this.clientRepository.existsByEmail(clientDTO.email())){
            throw new RuntimeException("Email já cadastrado");
        }else {
            ClientEntity clientEntity = new ClientEntity(clientDTO);
            return new ClientDTO(clientRepository.save(clientEntity));
        }
    }

    public List<ClientDTO> findByName(String name){
        String str = "cannot be empty.";

        if (name.isBlank()){
            throw new InvalidDataException("The first name " + str);
        }

        List<ClientEntity> clients = this.clientRepository.findByFirstName(name);

        if (clients.isEmpty()) {
            throw new ClientNotFoundException("No clients found with the name: " + name);
        }

        return clients.stream().map(ClientDTO::fromEntity).toList();
    }

    public List<ClientDTO> findAll(){
        return this.clientRepository.findAll().stream().map(ClientDTO::fromEntity).toList();
    }

    @Transactional
    public ClientDTO update(UUID id, ClientDTO clientDTO) {
        this.validate = new ValidationService(clientDTO);

        ClientEntity existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Cliente não encontrado"));

        if (!existingClient.getEmail().equals(clientDTO.email())) {
            AuthenticationEntity existingAuth = existingClient.getAuthentication();
            if (existingAuth != null) {
                existingAuth.setEmail(clientDTO.email());
                existingAuth.setPassword(clientDTO.password());
            } else {
                AuthenticationEntity newAuth = new AuthenticationEntity(clientDTO.email(), clientDTO.password(), existingClient);
                existingClient.setAuthentication(newAuth);
            }
        } else {
            existingClient.getAuthentication().setPassword(clientDTO.password());
        }

        existingClient.setFirstName(clientDTO.firstName());
        existingClient.setLastName(clientDTO.lastName());
        existingClient.setPhoneNumber(clientDTO.phoneNumber());

        if (clientDTO.image() != null) {
            existingClient.setImage(new ImageClientEntity(clientDTO.image()));
        }

        clientRepository.save(existingClient);

        return new ClientDTO(existingClient);
    }


    public AuthenticationEntity findAuthenticationByEmail(String email) {
        return authentication.findByEmail(email);
    }
}
