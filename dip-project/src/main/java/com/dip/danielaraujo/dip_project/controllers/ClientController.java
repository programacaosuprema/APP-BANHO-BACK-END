package com.dip.danielaraujo.dip_project.controllers;

import com.dip.danielaraujo.dip_project.exceptions.InvalidDataFromClientException;
import com.dip.danielaraujo.dip_project.dtos.ClientDTO;
import com.dip.danielaraujo.dip_project.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@CrossOrigin("*")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping()
    public ResponseEntity<?> createClient(@RequestBody ClientDTO clientDTO) {
        try {
            ClientDTO createdClient = clientService.create(clientDTO);
            return ResponseEntity.ok(createdClient);
        } catch (InvalidDataFromClientException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> findClientByName(@PathVariable String name) {
        try{
            ClientDTO clientDTO = clientService.findByName(name);

            if (clientDTO != null) {
                return ResponseEntity.ok(clientDTO);
            } else {
                return ResponseEntity.notFound().build();
            }

        } catch (InvalidDataFromClientException e) {
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

        } catch (InvalidDataFromClientException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
