package com.dip.danielaraujo.dip_project;

import com.dip.danielaraujo.dip_project.dtos.ClientDTO;
import com.dip.danielaraujo.dip_project.dtos.ImageDTO;
import com.dip.danielaraujo.dip_project.entities.ClientEntity;
import com.dip.danielaraujo.dip_project.services.ClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ClientServiceTest {
    private ClientService clientService;

    @Test
    public void createClient() {
        // Criando um DTO de imagem
        ImageDTO imageDTO = new ImageDTO(null, "nome da imagem", "src/img/");

        // Criando um DTO de cliente
        ClientDTO clientDTO = new ClientDTO(null, "Daniel", "da Silva de Araújo",
                "dannielaraujooficial", "98989060439", imageDTO, "12345678");

        // Chamando o método do service para criar o cliente
        List<ClientEntity> clients = clientService.create(clientDTO);

        // Verificando se o cliente foi criado corretamente
        assertEquals(1, clients.size());
        assertEquals(clientDTO, ClientDTO.fromEntity(clients.get(0)));
    }
}
