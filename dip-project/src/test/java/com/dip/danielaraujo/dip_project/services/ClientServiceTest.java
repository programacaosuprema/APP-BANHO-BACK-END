package com.dip.danielaraujo.dip_project.services;

import com.dip.danielaraujo.dip_project.exceptions.EmptyStringException;
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
    @Autowired
    private ClientService clientService;

    private ClientDTO createClient(String firstName, String lastName, String email, String phoneNumber, String password){
        ImageDTO imageDTO = new ImageDTO(null, "monalisa", "src/img/");

        return new ClientDTO(null, firstName, lastName, email, phoneNumber , imageDTO, password);
    }

    @Test
    @DisplayName("Should create a client in database and return just one client from database")
    public void createClientSuccess() {
        this.clientService.create(this.createClient("Daniel", "Araújo",
                "daniel@gmail.com", "98988060439", "123344"));

        ClientDTO client = this.clientService.findByName("Daniel");

        assertEquals(1, client.id());
    }

    @Test
    @DisplayName("Should not get client from database when client not exists")
    public void ClientIsNotFound() {
        ClientDTO client = this.clientService.findByName("Daniel");
        assertNull(client);
    }

    @Test
    @DisplayName("Should throw a EmptyStringException when client first name is empty")
    public void FindClientWhenClientFirstNameIsEmpty() {
        assertThrowsExactly(EmptyStringException.class, () -> this.clientService.findByName(""));
    }

    @Test
    @DisplayName("Should throw a EmptyStringException when client first name is empty")
    public void createClientEmptyStringExceptionWhenClientFirstNameIsEmpty() {
        ClientDTO clientDTO = this.createClient("", "Araújo",
                "daniel@gmail.com", "98989002439", "123344");
        assertThrowsExactly(EmptyStringException.class, () -> this.clientService.create(clientDTO));
    }
    @Test
    @DisplayName("Should throw a EmptyStringException when client email is empty")
    public void createClientEmptyStringExceptionWhenClientEmailIsEmpty() {
        ClientDTO clientDTO = this.createClient("Daniel", "Araújo",
                "", "98985240439", "123344");
        assertThrowsExactly(EmptyStringException.class, () -> this.clientService.create(clientDTO));
    }
    @Test
    @DisplayName("Should throw a EmptyStringException when client phone number is empty")
    public void createClientEmptyStringExceptionWhenClientPhoneNumberIsEmpty() {
        ClientDTO clientDTO = this.createClient("Daniel", "Araújo",
                "daniel@gmail.com", "", "123344");
        assertThrowsExactly(EmptyStringException.class, () -> this.clientService.create(clientDTO));
    }
    @Test
    @DisplayName("Should throw a EmptyStringException when client password is empty")
    public void createClientEmptyStringExceptionWhenClientPasswordIsEmpty() {
        ClientDTO clientDTO = this.createClient("Daniel", "Araújo",
                "daniel@gmail.com", "98981060421", "");
        assertThrowsExactly(EmptyStringException.class, () -> this.clientService.create(clientDTO));
    }



}
