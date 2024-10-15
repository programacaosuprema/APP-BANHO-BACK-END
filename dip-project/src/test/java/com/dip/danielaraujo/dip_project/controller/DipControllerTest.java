package com.dip.danielaraujo.dip_project.controller;

import com.dip.danielaraujo.dip_project.dtos.DipDTO;
import com.dip.danielaraujo.dip_project.enums.AccessTypeEnum;
import com.dip.danielaraujo.dip_project.services.DipService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import com.dip.danielaraujo.dip_project.controllers.DipController;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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

    @BeforeEach
    void setUp() {
        this.dipDTO = new DipDTO(null, "Beautiful River", "A calm and beautiful river.",
                "State", "City", new BigDecimal("25.5"), AccessTypeEnum.PUBLIC, "location");

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
        Mockito.when(dipService.findById(anyLong())).thenReturn(dipDTO);

        mockMvc.perform(get("/dips/{id}", 1L))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.name").value(dipDTO.name()));
    }

    @Test
    void getDipById_ReturnsBadRequest_WhenDipNotFound() throws Exception {
        Mockito.when(dipService.findById(anyLong())).thenThrow(new RuntimeException("Dip not found"));

        mockMvc.perform(get("/dips/{id}", 1L))
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
        Mockito.when(dipService.update(anyLong(), any(DipDTO.class))).thenReturn(dipDTO);

        mockMvc.perform(put("/dips/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dipDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(dipDTO.name()));
    }

    @Test
    void updateDip_ReturnsBadRequest_WhenUpdateFails() throws Exception {
        Mockito.when(dipService.update(anyLong(), any(DipDTO.class))).thenThrow(new RuntimeException("Dip update failed"));

        mockMvc.perform(put("/dips/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dipDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Dip update failed"));
    }
}
