package com.dip.danielaraujo.dip_project.services;

import com.dip.danielaraujo.dip_project.enums.UserRole;
import com.dip.danielaraujo.dip_project.exceptions.ClientNotFoundException;
import com.dip.danielaraujo.dip_project.exceptions.InvalidDataException;
import com.dip.danielaraujo.dip_project.dtos.ClientDTO;
import com.dip.danielaraujo.dip_project.entities.ClientEntity;
import com.dip.danielaraujo.dip_project.infra.security.TokenService;
import com.dip.danielaraujo.dip_project.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;;
import java.util.List;
import java.util.UUID;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AuthenticationService authService;

    @Autowired
    private TokenService tokenService;

    public ClientDTO findById(UUID id){
        return new ClientDTO(this.clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException("Client not found")));
    }

    public ClientDTO create(ClientDTO clientDTO){

        ValidationService validate = new ValidationService(clientDTO);

        if(this.clientRepository.existsByEmail(clientDTO.email())){
            throw new RuntimeException("Email já cadastrado");
        }else {
            UserRole role = UserRole.CLIENT;
            authService.register(clientDTO.email(), clientDTO.password(), role);

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

    public ClientDTO findByToken(String token) {
        if (token == null || token.trim().isEmpty()) {
            throw new InvalidDataException("Token cannot be empty.");
        }else if (tokenService.validateToken(token).isBlank()){
            throw new InvalidDataException("Invalid token.");
        }else{
            String email = tokenService.getClientEmailAfterDecodeToken(token);
            return new ClientDTO(this.clientRepository.findByEmail(email));
        }
    }
}
