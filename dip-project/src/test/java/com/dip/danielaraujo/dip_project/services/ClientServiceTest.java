package com.dip.danielaraujo.dip_project.services;

import com.dip.danielaraujo.dip_project.entities.AuthenticationEntity;
import com.dip.danielaraujo.dip_project.exceptions.ClientNotFoundException;
import com.dip.danielaraujo.dip_project.exceptions.InvalidDataException;
import com.dip.danielaraujo.dip_project.dtos.ClientDTO;
import com.dip.danielaraujo.dip_project.dtos.ImageDTO;
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
    private final  String lastName = "Araújo";
    private final String email = "daniel@gmail.com";
    private final String phoneNumber = "98988060439";
    private final ImageDTO imageDTO = new ImageDTO(null, "monalisa", "src/img/");
    private final String password = "Teste123#";
    @Autowired
    private ClientService clientService;

    private ClientDTO createClient(String firstName, String lastName, String email, String phoneNumber, ImageDTO imageDTO, String password){
        return new ClientDTO(null, firstName, lastName, email, phoneNumber , imageDTO, password);
    }

    @Test
    @DisplayName("Should create a client with a image in database and return just one client from database")
    public void createClientWithNotNullImageSuccess() {
        ClientDTO createdClient = this.clientService.create(this.createClient(this.name, this.lastName,
                this.email, this.phoneNumber, this.imageDTO , this.password));

        assertEquals(createdClient.firstName(), this.name);
        assertEquals(createdClient.lastName(), this.lastName);
        assertEquals(createdClient.email(), this.email);
        assertEquals(createdClient.phoneNumber(), this.phoneNumber);
        assertEquals(createdClient.image().name(), this.imageDTO.name());
        assertEquals(createdClient.image().src(), this.imageDTO.src());
        assertEquals(createdClient.password(), password);
    }

    @Test
    @DisplayName("Should create a client with valid authentication")
    public void createClientWithAuthenticationSuccess() {
        ClientDTO createdClient = this.clientService.create(this.createClient(this.name, this.lastName,
                this.email, this.phoneNumber, this.imageDTO , this.password));
        assertEquals(createdClient.firstName(), this.name);

        AuthenticationEntity auth = this.clientService.findAuthenticationByEmail(this.email);
        assertNotNull(auth);
        assertEquals(auth.getEmail(), this.email);
        assertEquals(auth.getPassword(), this.password);
    }


    @Test
    @DisplayName("Should create a client with a image in database and return just one client from database")
    public void createClientWithJDBCIntegrityConstraintViolationExceptionSuccess() {
        this.clientService.create(this.createClient(this.name, this.lastName,
                this.email, this.phoneNumber, this.imageDTO , this.password));
        ClientDTO createdClient2 = this.clientService.create(this.createClient(this.name, this.lastName,
                "email2@gmail.com", this.phoneNumber, this.imageDTO , this.password));

        assertThrowsExactly(RuntimeException.class, () -> this.clientService.create(createdClient2));
    }

    @Test
    @DisplayName("Should create a client with out a image in database and return just one client from database")
    public void createClientWithNullImageSuccess() {
        ClientDTO createdClient = this.clientService.create(this.createClient(this.name, this.lastName,
                this.email, this.phoneNumber, null , this.password));

        assertEquals(createdClient.firstName(), this.name);
        assertEquals(createdClient.lastName(), this.lastName);
        assertEquals(createdClient.email(), this.email);
        assertEquals(createdClient.phoneNumber(), this.phoneNumber);
        assertNull(createdClient.image().name());
        assertNull(createdClient.image().src());
        assertEquals(createdClient.password(), password);
    }

    @Test
    @DisplayName("Should update a client and return updated client from database")
    public void updateClientSuccess(){

        ClientDTO createdClient = this.clientService.create(this.createClient(this.name, this.lastName,
                this.email, this.phoneNumber, null , this.password));

        Long client_id = createdClient.id();

        String updated_name = "João";
        String updated_lastName = "Batista";
        String updated_email = "daniel123@gmail.com";
        String updated_phoneNumber = "98982818330";


        ClientDTO updatedClient = new ClientDTO(null, updated_name, updated_lastName, updated_email,
                updated_phoneNumber, createdClient.image(), createdClient.password());

        ClientDTO result = this.clientService.update(client_id, updatedClient);

        assertEquals(result.firstName(), updated_name);
        assertEquals(result.lastName(), updated_lastName);
        assertEquals(result.email(), updated_email);
        assertEquals(result.phoneNumber(), updated_phoneNumber);
    }

    @Test
    @DisplayName("Should not get client from database when client not exists")
    public void ClientIsNotFound() {
        assertThrowsExactly(ClientNotFoundException.class, () -> this.clientService.findByName("Daniel"));
    }

    @Test
    @DisplayName("Should throw a InvalidDataFromClientException when client first name is empty")
    public void FindClientWhenClientFirstNameIsEmpty() {
        assertThrowsExactly(InvalidDataException.class, () -> this.clientService.findByName(""));
    }

    @Test
    @DisplayName("Should throw a InvalidDataFromClientException when client first name is empty")
    public void createClientInvalidDataFromClientExceptionWhenClientFirstNameIsEmpty() {
            ClientDTO createdClient = this.createClient("", this.lastName,
                    this.email, this.phoneNumber, this.imageDTO , this.password);
            assertThrowsExactly(InvalidDataException.class, () -> this.clientService.create(createdClient));
    }
    @Test
    @DisplayName("Should throw a InvalidDataFromClientException when client email is empty")
    public void createClientInvalidDataFromClientExceptionWhenClientEmailIsEmpty() {
        ClientDTO createdClient = this.createClient(this.name, this.lastName,
                "", this.phoneNumber, this.imageDTO , this.password);
        assertThrowsExactly(InvalidDataException.class, () -> this.clientService.create(createdClient));
    }
    @Test
    @DisplayName("Should throw a InvalidDataFromClientException when client phone number is empty")
    public void createClientInvalidDataFromClientExceptionWhenClientPhoneNumberIsEmpty() {
        ClientDTO createdClient = this.createClient(this.name, this.lastName,
                this.email, "", this.imageDTO , this.password);
        assertThrowsExactly(InvalidDataException.class, () -> this.clientService.create(createdClient));
    }
    @Test
    @DisplayName("Should throw a InvalidDataFromClientException when client password is empty")
    public void createClientInvalidDataFromClientExceptionWhenClientPasswordIsEmpty() {
        ClientDTO createdClient = this.createClient(this.name, this.lastName,
                this.email, this.phoneNumber, this.imageDTO , "");
        assertThrowsExactly(InvalidDataException.class, () -> this.clientService.create(createdClient));
    }

    @Test
    @DisplayName("Should throw a InvalidDataFromClientException when client name is invalid")
    public void createClientInvalidDataFromClientExceptionWhenClientFirstNameIsInvalid() {
        ClientDTO createdClient = this.createClient("Daniel8000", this.lastName,
                this.email, this.phoneNumber, this.imageDTO , this.password);
        assertThrowsExactly(InvalidDataException.class, () -> this.clientService.create(createdClient));
    }
    @Test
    @DisplayName("Should throw a InvalidDataFromClientException client last name is invalid")
    public void createClientInvalidDataFromClientExceptionWhenClientLastNameIsInvalid() {
        ClientDTO createdClient = this.createClient(this.name, "Araujo 80@#",
                this.email, this.phoneNumber, this.imageDTO , this.password);
        assertThrowsExactly(InvalidDataException.class, () -> this.clientService.create(createdClient));
    }
    @Test
    @DisplayName("Should throw a InvalidDataFromClientException when client email is invalid")
    public void createClientInvalidDataFromClientExceptionWhenClientEmailIsInvalid() {
        ClientDTO createdClient = this.createClient(this.name, this.lastName,
                "danielgmail.com", this.phoneNumber, this.imageDTO , this.password);
        assertThrowsExactly(InvalidDataException.class, () -> this.clientService.create(createdClient));
    }
    @Test
    @DisplayName("Should throw a InvalidDataFromClientException when client phone number is invalid")
    public void createClientInvalidDataFromClientExceptionWhenClientPhoneNumberIsInvalid() {
        ClientDTO createdClient = this.createClient(this.name, this.lastName,
                this.phoneNumber, "998319", this.imageDTO , this.password);
        assertThrowsExactly(InvalidDataException.class, () -> this.clientService.create(createdClient));
    }
    @Test
    @DisplayName("Should throw a InvalidDataFromClientException when client password is invalid")
    public void createClientInvalidDataFromClientExceptionWhenClientPasswordIsInvalid() {
        ClientDTO createdClient = this.createClient(this.name, this.lastName,
                this.phoneNumber, this.phoneNumber, this.imageDTO , "1234567");
        assertThrowsExactly(InvalidDataException.class, () -> this.clientService.create(createdClient));
    }



}
