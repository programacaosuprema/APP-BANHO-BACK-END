package com.dip.danielaraujo.dip_project.controllers;

import com.dip.danielaraujo.dip_project.exceptions.InvalidDataException;
import com.dip.danielaraujo.dip_project.dtos.ClientDTO;
import com.dip.danielaraujo.dip_project.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clients")
@CrossOrigin("*")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping("/register")
    public ResponseEntity<?> createClient(@RequestBody @Valid ClientDTO clientDTO) {
        try {
            ClientDTO createdClient = clientService.create(clientDTO);
            return ResponseEntity.ok(createdClient);
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> findClientByToken(@RequestHeader("Authorization") String authorizationHeader) {
        try {
            String token = authorizationHeader.replace("Bearer ", "");
            ClientDTO clientDTO = clientService.findByToken(token);

            if (clientDTO!= null) {
                return ResponseEntity.ok(clientDTO);
            } else {
                return ResponseEntity.notFound().build();
            }

        } catch (InvalidDataException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

   /* @PutMapping("/{id}")
    public ResponseEntity<?> updateClient(@PathVariable String id, @RequestBody ClientDTO clientDTO) {
        try {
            UUID uuid;
            try {
                uuid = UUID.fromString(id);
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body("UUID inválido");
            }
            ClientDTO updatedClient = clientService.update(uuid, clientDTO);
            return ResponseEntity.ok(updatedClient);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }*/

    @GetMapping("/{name}")
    public ResponseEntity<?> findClientByName(@PathVariable String name) {
        try{
            List<ClientDTO> clients = clientService.findByName(name);

            if (!clients.isEmpty()) {
                return ResponseEntity.ok(clients);
            } else {
                return ResponseEntity.notFound().build();
            }

        } catch (InvalidDataException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<?> findAll() {
        try{
            List<ClientDTO> clientDTO = clientService.findAll();

            if (clientDTO != null) {
                return ResponseEntity.ok(clientDTO);
            } else {
                return ResponseEntity.notFound().build();
            }

        } catch (InvalidDataException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
