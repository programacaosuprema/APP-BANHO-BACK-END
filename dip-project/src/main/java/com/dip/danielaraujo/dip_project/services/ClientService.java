package com.dip.danielaraujo.dip_project.services;

import com.dip.danielaraujo.dip_project.entities.AuthenticationEntity;
import com.dip.danielaraujo.dip_project.entities.ImageEntity;
import com.dip.danielaraujo.dip_project.exceptions.ClientNotFoundException;
import com.dip.danielaraujo.dip_project.exceptions.InvalidDataFromClientException;
import com.dip.danielaraujo.dip_project.dtos.ClientDTO;
import com.dip.danielaraujo.dip_project.entities.ClientEntity;
import com.dip.danielaraujo.dip_project.repositories.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;;
import java.util.List;
import com.dip.danielaraujo.dip_project.repositories.AutheticationRepository;
@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ValidationService validate;

    @Autowired
    private AutheticationRepository authentication;

    public ClientDTO findById(Long id){
        return new ClientDTO(this.clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException("Client not found")));
    }

    public ClientDTO create(ClientDTO clientDTO){

        this.validate.validateClient(clientDTO);

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
            throw new InvalidDataFromClientException("The first name " + str);
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
    public ClientDTO update(Long id, ClientDTO clientDTO) {
        ClientEntity existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));

        if (!existingClient.getEmail().equals(clientDTO.email())) {
            this.authentication.delete(existingClient.getAuthentication());

            AuthenticationEntity newAuth = new AuthenticationEntity(clientDTO.email(), clientDTO.password());
            existingClient.setAuthentication(newAuth);  // Atualiza a autenticação
        }

        // Atualizar outros dados do cliente
        existingClient.setFirstName(clientDTO.firstName());
        existingClient.setLastName(clientDTO.lastName());
        existingClient.setPhoneNumber(clientDTO.phoneNumber());

        if (clientDTO.image() != null) {
            existingClient.setImage(new ImageEntity(clientDTO.image()));
        }

        clientRepository.save(existingClient);

        return new ClientDTO(existingClient);
    }
}
