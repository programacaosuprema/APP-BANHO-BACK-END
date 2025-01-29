package com.dip.danielaraujo.dip_project.controller;

import com.dip.danielaraujo.dip_project.controllers.ClientController;
import com.dip.danielaraujo.dip_project.dtos.ClientDTO;
import com.dip.danielaraujo.dip_project.services.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ClientControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientController clientController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();
    }

    // Helper method to convert objects to JSON strings
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testCreateClientSuccess() throws Exception {
        // Ajuste para o ClientDTO record
        ClientDTO clientDTO = new ClientDTO(UUID.randomUUID(), "John", "Doe", "john.doe@example.com", "555-1234", null, "password123");

        when(clientService.create(any(ClientDTO.class))).thenReturn(clientDTO);

        mockMvc.perform(post("/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(clientDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));

        verify(clientService, times(1)).create(any(ClientDTO.class));
    }

    @Test
    public void testCreateClientBadRequest() throws Exception {
        when(clientService.create(any(ClientDTO.class))).thenThrow(new RuntimeException("Error creating client"));

        ClientDTO clientDTO = new ClientDTO(UUID.randomUUID(), "John", "Doe", "john.doe@example.com", "555-1234", null, "password123");

        mockMvc.perform(post("/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(clientDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Error creating client"));

        verify(clientService, times(1)).create(any(ClientDTO.class));
    }

    /*@Test
    public void testUpdateClientSuccess() throws Exception {
        UUID id = UUID.randomUUID();
        ClientDTO clientDTO = new ClientDTO(id, "John", "Doe", "john.doe@example.com", "555-1234", null, "newPassword");

        when(clientService.update(eq(id), any(ClientDTO.class))).thenReturn(clientDTO);

        mockMvc.perform(put("/clients/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(clientDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"))
                .andExpect(jsonPath("$.password").value("newPassword"));

        verify(clientService, times(1)).update(eq(id), any(ClientDTO.class));
    }*/

    @Test
    public void testFindClientByNameSuccess() throws Exception {
        List<ClientDTO> clients = Arrays.asList(
                new ClientDTO(UUID.randomUUID(), "John", "Doe", "john.doe@example.com", "555-1234", null, "password123"),
                new ClientDTO(UUID.randomUUID(), "Jane", "Doe", "jane.doe@example.com", "555-4321", null, "password321")
        );

        when(clientService.findByName("Doe")).thenReturn(clients);

        mockMvc.perform(get("/clients/Doe"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[1].firstName").value("Jane"));

        verify(clientService, times(1)).findByName("Doe");
    }

    @Test
    public void testFindAllClientsSuccess() throws Exception {
        List<ClientDTO> clients = Arrays.asList(
                new ClientDTO(UUID.randomUUID(), "John", "Doe", "john.doe@example.com", "555-1234", null, "password123"),
                new ClientDTO(UUID.randomUUID(), "Jane", "Doe", "jane.doe@example.com", "555-4321", null, "password321")
        );

        when(clientService.findAll()).thenReturn(clients);

        mockMvc.perform(get("/clients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[1].firstName").value("Jane"));

        verify(clientService, times(1)).findAll();
    }

    @Test
    public void testFindClientByNameNotFound() throws Exception {
        when(clientService.findByName("Unknown")).thenReturn(List.of());

        mockMvc.perform(get("/clients/Unknown"))
                .andExpect(status().isNotFound());

        verify(clientService, times(1)).findByName("Unknown");
    }
}
