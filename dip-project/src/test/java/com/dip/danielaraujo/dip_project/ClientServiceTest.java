package com.dip.danielaraujo.dip_project;

import com.dip.danielaraujo.dip_project.dtos.ClientDTO;
import com.dip.danielaraujo.dip_project.entities.ClientEntity;
import com.dip.danielaraujo.dip_project.services.ClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ClientServiceTest {
    @Autowired
    private ClientService clientService = new ClientService();
    @Test
    public void createClient(){
        //
    }
}
