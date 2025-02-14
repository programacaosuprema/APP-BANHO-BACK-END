package com.dip.danielaraujo.dip_project.controller;

import com.dip.danielaraujo.dip_project.controllers.ClientController;
import com.dip.danielaraujo.dip_project.dtos.ClientDTO;
import com.dip.danielaraujo.dip_project.dtos.ImageDTO;
import com.dip.danielaraujo.dip_project.services.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.MethodArgumentNotValidException;

public class ClientControllerTest {

    private final String name = "Daniel";
    private final String lastName = "Araujo";
    private final String email = "daniel@gmail.com";
    private final String phoneNumber = "98988060439";
    private final ImageDTO imageDTO = new ImageDTO(null, "monalisa", "src/img/", "JPG");
    private final String password = "Teste123#";

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

    private ClientDTO createClient(String firstName, String lastName, String email, String phoneNumber, ImageDTO imageDTO, String password) {
        return new ClientDTO(null, firstName, lastName, email, phoneNumber, imageDTO, password);
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
        ClientDTO clientDTO = new ClientDTO(UUID.randomUUID(), name, lastName, email, phoneNumber, imageDTO, password);

        when(clientService.create(any(ClientDTO.class))).thenReturn(clientDTO);

        mockMvc.perform(post("/clients/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(clientDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(name))
                .andExpect(jsonPath("$.lastName").value(lastName))
                .andExpect(jsonPath("$.email").value(email));

        verify(clientService, times(1)).create(any(ClientDTO.class));
    }

    @Test
    public void testCreateClientBadRequest() throws Exception {
       /* when(clientService.create(any(ClientDTO.class))).thenThrow(new RuntimeException("Error creating client"));

        ClientDTO clientDTO = new ClientDTO(UUID.randomUUID(), "John", "Doe", "john.doe@example.com", "555-1234", null, "password123");

        mockMvc.perform(post("/clients/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(clientDTO)))
                .andExpect(status().isBadRequest());

        verify(clientService, times(1)).create(any(ClientDTO.class));*/
    }

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

    @Test
    @DisplayName("Should throw MethodArgumentNotValidException when creating a client with invalid data")
    public void createClientWithMethodArgumentNotValidException() {
        /*assertInvalidDataMethodArgumentNotValidException("", lastName, email, phoneNumber, imageDTO, password);
        assertInvalidDataMethodArgumentNotValidException(name, "", email, phoneNumber, imageDTO, password);
        assertInvalidDataMethodArgumentNotValidException(name, lastName, "", phoneNumber, imageDTO, password);
        assertInvalidDataMethodArgumentNotValidException(name, lastName, email, phoneNumber, imageDTO, "");
        assertInvalidDataMethodArgumentNotValidException("Daniel8000", lastName, email, phoneNumber, imageDTO, password);
        assertInvalidDataMethodArgumentNotValidException(name, "Araujo 80@#", email, phoneNumber, imageDTO, password);
        assertInvalidDataMethodArgumentNotValidException(name, lastName, "danielgmail.com", phoneNumber, imageDTO, password);
        assertInvalidDataMethodArgumentNotValidException(name, lastName, email, "998319", imageDTO, password);
        assertInvalidDataMethodArgumentNotValidException(name, lastName, email, phoneNumber, imageDTO, "1234567");
        ImageDTO image = new ImageDTO(null, "monalisa", "src/img/", "PDF");
        assertInvalidDataMethodArgumentNotValidException(name, lastName, email, phoneNumber, image, password);*/
    }

    private void assertInvalidDataMethodArgumentNotValidException(String firstName, String lastName, String email, String phoneNumber, ImageDTO imageDTO, String password) {
        ClientDTO clientDTO = createClient(firstName, lastName, email, phoneNumber, imageDTO, password);
        assertThrows(MethodArgumentNotValidException.class, () -> clientService.create(clientDTO));
    }
}
