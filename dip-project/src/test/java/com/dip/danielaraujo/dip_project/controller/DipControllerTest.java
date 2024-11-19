package com.dip.danielaraujo.dip_project.controller;

import com.dip.danielaraujo.dip_project.controllers.DipController;
import com.dip.danielaraujo.dip_project.dtos.DipDTO;
import com.dip.danielaraujo.dip_project.dtos.ImageDipDTO;
import com.dip.danielaraujo.dip_project.services.DipService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DipController.class)
public class DipControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DipService dipService;

    @Autowired
    private ObjectMapper objectMapper;

    private DipDTO dipDTO;
    private UUID dipId; // Declare UUID para ID

    @BeforeEach
    void setUp() {
        // Inicializa o UUID para o dipId
        dipId = UUID.randomUUID(); // Agora o ID é UUID

        ImageDipDTO image1 = new ImageDipDTO(UUID.randomUUID(), "dip1", "https://dip.com.br/src/images", "JPEG");
        ImageDipDTO image2 = new ImageDipDTO(UUID.randomUUID(), "dip2", "https://dip.com.br/src/images", "PNG");

        List<ImageDipDTO> images = new ArrayList<>() {
            {
                add(image1);
                add(image2);
            }
        };

        // Agora o dipDTO é criado com o UUID dipId
        this.dipDTO = new DipDTO(dipId, "Beautiful River", "A calm and beautiful river.",
                "State", "City", new BigDecimal("25.5"), "PRIVADO", "location", images);
    }

    @Test
    void createDip_ReturnsCreatedStatus() throws Exception {
        Mockito.when(dipService.create(any(DipDTO.class))).thenReturn(dipDTO);

        mockMvc.perform(post("/dips")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dipDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(dipDTO.name()));
    }

    @Test
    void getDipById_ReturnsDip_WhenDipExists() throws Exception {
        // Alteração: Passando o UUID correto
        Mockito.when(dipService.findById(any(UUID.class))).thenReturn(dipDTO);

        mockMvc.perform(get("/dips/{id}", dipId)) // Usando o UUID
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.name").value(dipDTO.name()));
    }

    @Test
    void getDipById_ReturnsBadRequest_WhenDipNotFound() throws Exception {
        // Alteração: Passando o UUID correto
        Mockito.when(dipService.findById(any(UUID.class))).thenThrow(new RuntimeException("Dip not found"));

        mockMvc.perform(get("/dips/{id}", dipId)) // Usando o UUID
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Dip not found"));
    }

    @Test
    void getDipsByName_ReturnsDips_WhenDipsExist() throws Exception {
        List<DipDTO> dipList = List.of(dipDTO);
        Mockito.when(dipService.findByName(any(String.class))).thenReturn(dipList);

        mockMvc.perform(get("/dips/search").param("name", "River"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(dipDTO.name()));
    }

    @Test
    void getDipsByName_ReturnsNotFound_WhenNoDipsFound() throws Exception {
        Mockito.when(dipService.findByName(any(String.class))).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/dips/search").param("name", "NonExistent"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllDips_ReturnsAllDips_WhenDipsExist() throws Exception {
        List<DipDTO> dipList = List.of(dipDTO);
        Mockito.when(dipService.findAll()).thenReturn(dipList);

        mockMvc.perform(get("/dips"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(dipDTO.name()));
    }

    @Test
    void getAllDips_ReturnsNotFound_WhenNoDipsExist() throws Exception {
        Mockito.when(dipService.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/dips"))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateDip_ReturnsUpdatedDip_WhenDipIsUpdated() throws Exception {
        Mockito.when(dipService.update(any(UUID.class), any(DipDTO.class))).thenReturn(dipDTO);

        mockMvc.perform(put("/dips/{id}", dipId) // Passando o UUID correto
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dipDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(dipDTO.name()));
    }

    @Test
    void updateDip_ReturnsBadRequest_WhenUpdateFails() throws Exception {
        Mockito.when(dipService.update(any(UUID.class), any(DipDTO.class))).thenThrow(new RuntimeException("Dip update failed"));

        mockMvc.perform(put("/dips/{id}", dipId) // Passando o UUID correto
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dipDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Dip update failed"));
    }
}
