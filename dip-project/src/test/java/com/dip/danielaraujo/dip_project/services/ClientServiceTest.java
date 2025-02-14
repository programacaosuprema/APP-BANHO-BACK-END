/*package com.dip.danielaraujo.dip_project.services;

import com.dip.danielaraujo.dip_project.dtos.ImageDTO;
import com.dip.danielaraujo.dip_project.entities.UserEntity;
import com.dip.danielaraujo.dip_project.exceptions.ClientNotFoundException;
import com.dip.danielaraujo.dip_project.dtos.ClientDTO;
import com.dip.danielaraujo.dip_project.infra.security.TokenService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class ClientServiceTest {

    private final String name = "Daniel";
    private final String lastName = "Araújo";
    private final String email = "daniel@gmail.com";
    private final String phoneNumber = "98988060439";
    private final ImageDTO imageDTO = new ImageDTO(null, "monalisa", "src/img/", "JPG");
    private final String password = "Teste123#";

    @MockBean
    private ClientService clientService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    private ClientDTO createClient(String firstName, String lastName, String email, String phoneNumber, ImageDTO imageDTO, String password) {
        return new ClientDTO(null, firstName, lastName, email, phoneNumber, imageDTO, password);
    }
    @Test
    @DisplayName("Should create a client, log in with authentication and return a token")
    public void createClientWithAuthenticationSuccess() {
        ClientDTO clientDTO = createClient(name, lastName, email, phoneNumber, imageDTO, password);
        ClientDTO createdClient = clientService.create(clientDTO);

        assertClientDetails(createdClient, name, lastName, email, phoneNumber, imageDTO);

        var userNamePassword = new UsernamePasswordAuthenticationToken(email,password);

        var auth = manager.authenticate(userNamePassword);

        var token = this.tokenService.generateToken((UserEntity) auth.getPrincipal());

        assertNotNull(token);
    }

    @Test
    @DisplayName("Should throw exception when creating a client with duplicate email")
    public void createClientDuplicateEmailThrowsException() {
        clientService.create(createClient(name, lastName, email, phoneNumber, imageDTO, password));

        ClientDTO duplicateClient = createClient(name, lastName, email, phoneNumber, imageDTO, password);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> clientService.create(duplicateClient));

        assertTrue(exception.getMessage().contains("Email já cadastrado"));
    }

    @Test
    @DisplayName("Should create a client without an image and return the client details")
    public void createClientWithoutImageSuccess() {
        ClientDTO clientDTO = createClient(name, lastName, email, phoneNumber, null, password);
        ClientDTO createdClient = clientService.create(clientDTO);

        assertClientDetails(createdClient, name, lastName, email, phoneNumber, null);
    }

    @Test
    @DisplayName("Should throw ClientNotFoundException when searching for a non-existent client")
    public void clientIsNotFound() {
        assertThrows(ClientNotFoundException.class, () -> clientService.findByName("Daniel"));
    }

    @Test
    @DisplayName("Should update a client and return the updated client details")
    public void updateClientSuccess() {
        /*ClientDTO clientDTO = createClient(name, lastName, email, phoneNumber, imageDTO, password);
        ClientDTO createdClient = clientService.create(clientDTO);

        String updatedName = "Daniel Updated";
        String updatedLastName = "Araújo Updated";
        String updatedEmail = "dani@gmail.com";
        String updatedPhoneNumber = "98988060439";
        ImageDTO updatedImageDTO = new ImageDTO(null, "monalisa", "src/img/", "JPG");
        String updatePassword = "Teste123#";

        Boolean teste = clientService.update(createdClient.id(), createClient(updatedName, updatedLastName, updatedEmail, updatedPhoneNumber, updatedImageDTO, updatePassword));

        assertEquals(true, teste);
        assertClientDetails(updatedClient, updatedName, updatedLastName, updatedEmail, updatedPhoneNumber, updatedImageDTO);
    }

    private void assertClientDetails(ClientDTO client, String expectedFirstName, String expectedLastName, String expectedEmail, String expectedPhoneNumber, ImageDTO expectedImage) {
        assertEquals(expectedFirstName, client.firstName());
        assertEquals(expectedLastName, client.lastName());
        assertEquals(expectedEmail, client.email());
        assertEquals(expectedPhoneNumber, client.phoneNumber());
        assertEquals(expectedImage != null ? expectedImage.name() : null, client.image() != null ? client.image().name() : null);
        assertEquals(expectedImage != null ? expectedImage.src() : null, client.image() != null ? client.image().src() : null);
    }


}
*/