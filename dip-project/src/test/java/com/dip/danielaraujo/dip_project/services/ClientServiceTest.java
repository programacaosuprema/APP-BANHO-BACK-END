package com.dip.danielaraujo.dip_project.services;

import com.dip.danielaraujo.dip_project.dtos.ImageClientDTO;
import com.dip.danielaraujo.dip_project.entities.AuthenticationEntity;
import com.dip.danielaraujo.dip_project.exceptions.ClientNotFoundException;
import com.dip.danielaraujo.dip_project.exceptions.InvalidDataException;
import com.dip.danielaraujo.dip_project.dtos.ClientDTO;
import com.dip.danielaraujo.dip_project.dtos.ImageDipDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class ClientServiceTest {

    private final String name = "Daniel";
    private final String lastName = "Araújo";
    private final String email = "daniel@gmail.com";
    private final String phoneNumber = "98988060439";
    private final ImageClientDTO imageDTO = new ImageClientDTO(null, "monalisa", "src/img/", "JPG");
    private final String password = "Teste123#";

    @Autowired
    private ClientService clientService;

    private ClientDTO createClient(String firstName, String lastName, String email, String phoneNumber, ImageDipDTO imageDTO, String password) {
        return new ClientDTO(null, firstName, lastName, email, phoneNumber, imageDTO, password);
    }

    @Test
    @DisplayName("Should create a client with an image and return the client details")
    public void createClientWithImageSuccess() {
        ClientDTO clientDTO = createClient(name, lastName, email, phoneNumber, imageDTO, password);
        ClientDTO createdClient = clientService.create(clientDTO);

        assertClientDetails(createdClient, name, lastName, email, phoneNumber, imageDTO, password);
    }

    @Test
    @DisplayName("Should create a client and save authentication details")
    public void createClientWithAuthenticationSuccess() {
        ClientDTO clientDTO = createClient(name, lastName, email, phoneNumber, imageDTO, password);
        ClientDTO createdClient = clientService.create(clientDTO);

        AuthenticationEntity auth = clientService.findAuthenticationByEmail(email);

        assertNotNull(auth);
        assertEquals(email, auth.getEmail());
        assertEquals(password, auth.getPassword());
    }

    @Test
    @DisplayName("Should throw exception when creating a client with duplicate email")
    public void createClientDuplicateEmailThrowsException() {
        clientService.create(createClient(name, lastName, email, phoneNumber, imageDTO, password));

        ClientDTO duplicateClient = createClient(name, lastName, "email2@gmail.com", phoneNumber, imageDTO, password);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> clientService.create(duplicateClient));

        assertTrue(exception.getMessage().contains("unique constraint violated")); // Adjust the message as needed
    }

    @Test
    @DisplayName("Should create a client without an image and return the client details")
    public void createClientWithoutImageSuccess() {
        ClientDTO clientDTO = createClient(name, lastName, email, phoneNumber, null, password);
        ClientDTO createdClient = clientService.create(clientDTO);

        assertClientDetails(createdClient, name, lastName, email, phoneNumber, null, password);
    }

    @Test
    @DisplayName("Should update a client and return updated details")
    public void updateClientSuccess() {
        ClientDTO clientDTO = createClient(name, lastName, email, phoneNumber, null, password);
        ClientDTO createdClient = clientService.create(clientDTO);

        Long clientId = createdClient.id();
        ClientDTO updatedClient = new ClientDTO(null, "João", "Batista", "daniel123@gmail.com", "98982818330", createdClient.image(), createdClient.password());

        ClientDTO result = clientService.update(clientId, updatedClient);

        assertClientDetails(result, "João", "Batista", "daniel123@gmail.com", "98982818330", null, password);
    }

    @Test
    @DisplayName("Should throw ClientNotFoundException when searching for a non-existent client")
    public void clientIsNotFound() {
        assertThrows(ClientNotFoundException.class, () -> clientService.findByName("Daniel"));
    }

    @Test
    @DisplayName("Should throw InvalidDataException when creating a client with invalid data")
    public void createClientWithInvalidDataThrowsException() {
        assertInvalidDataExceptionForClient("", lastName, email, phoneNumber, imageDTO, password);
        assertInvalidDataExceptionForClient(name, "", email, phoneNumber, imageDTO, password);
        assertInvalidDataExceptionForClient(name, lastName, "", phoneNumber, imageDTO, password);
        assertInvalidDataExceptionForClient(name, lastName, email, phoneNumber, imageDTO, "");
        assertInvalidDataExceptionForClient("Daniel8000", lastName, email, phoneNumber, imageDTO, password);
        assertInvalidDataExceptionForClient(name, "Araujo 80@#", email, phoneNumber, imageDTO, password);
        assertInvalidDataExceptionForClient(name, lastName, "danielgmail.com", phoneNumber, imageDTO, password);
        assertInvalidDataExceptionForClient(name, lastName, email, "998319", imageDTO, password);
        assertInvalidDataExceptionForClient(name, lastName, email, phoneNumber, imageDTO, "1234567");
    }

    private void assertClientDetails(ClientDTO client, String expectedFirstName, String expectedLastName, String expectedEmail, String expectedPhoneNumber, ImageDipDTO expectedImage, String expectedPassword) {
        assertEquals(expectedFirstName, client.firstName());
        assertEquals(expectedLastName, client.lastName());
        assertEquals(expectedEmail, client.email());
        assertEquals(expectedPhoneNumber, client.phoneNumber());
        assertEquals(expectedImage != null ? expectedImage.name() : null, client.image() != null ? client.image().name() : null);
        assertEquals(expectedImage != null ? expectedImage.src() : null, client.image() != null ? client.image().src() : null);
        assertEquals(expectedPassword, client.password());
    }

    private void assertInvalidDataExceptionForClient(String firstName, String lastName, String email, String phoneNumber, ImageDipDTO imageDTO, String password) {
        ClientDTO clientDTO = createClient(firstName, lastName, email, phoneNumber, imageDTO, password);
        assertThrows(InvalidDataException.class, () -> clientService.create(clientDTO));
    }
}
