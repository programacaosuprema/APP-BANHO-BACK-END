package com.dip.danielaraujo.dip_project.services;

import com.dip.danielaraujo.dip_project.dtos.DipDTO;
import com.dip.danielaraujo.dip_project.entities.DipEntity;
import com.dip.danielaraujo.dip_project.enums.AccessTypeEnum;
import com.dip.danielaraujo.dip_project.exceptions.DipNotFoundException;
import com.dip.danielaraujo.dip_project.repositories.DipRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DipServiceTest {

    @Mock
    private DipRepository dipRepository;

    @Mock
    private ValidationService validationService;

    @InjectMocks
    private DipService dipService;

    private DipDTO dipDTO;
    private DipEntity dipEntity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Instanciando objetos de teste
        dipDTO = new DipDTO(1L, "Dip Test", "Beautiful dip", "MA", "São Luís", new BigDecimal("25.0"), AccessTypeEnum.PUBLIC, "Some location");
        dipEntity = new DipEntity(dipDTO);
    }

    @Test
    public void testCreateDip() {
        when(dipRepository.save(any(DipEntity.class))).thenReturn(dipEntity);

        // Aqui a validação será feita pelo construtor do ValidationService
        DipDTO result = dipService.create(dipDTO);

        assertNotNull(result);
        assertEquals(dipDTO.name(), result.name());
        assertEquals(dipDTO.description(), result.description());

        verify(dipRepository, times(1)).save(any(DipEntity.class));
        // Não precisa mais do verify(validationService), pois a validação é feita no construtor
    }

    @Test
    public void testFindByIdSuccess() {
        when(dipRepository.findById(1L)).thenReturn(Optional.of(dipEntity));

        DipDTO result = dipService.findById(1L);

        assertNotNull(result);
        assertEquals(dipDTO.name(), result.name());
        assertEquals(dipDTO.description(), result.description());

        verify(dipRepository, times(1)).findById(1L);
    }

    @Test
    public void testFindByIdNotFound() {
        when(dipRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(DipNotFoundException.class, () -> {
            dipService.findById(1L);
        });

        String expectedMessage = "Dip com ID 1 não encontrado";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

        verify(dipRepository, times(1)).findById(1L);
    }

    @Test
    public void testFindByNameSuccess() {
        when(dipRepository.findByNameContainingIgnoreCase("Dip")).thenReturn(Collections.singletonList(dipEntity));

        List<DipDTO> result = dipService.findByName("Dip");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(dipDTO.name(), result.get(0).name());

        verify(dipRepository, times(1)).findByNameContainingIgnoreCase("Dip");
    }

    @Test
    public void testFindAllSuccess() {
        when(dipRepository.findAll()).thenReturn(Collections.singletonList(dipEntity));

        List<DipDTO> result = dipService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(dipDTO.name(), result.get(0).name());

        verify(dipRepository, times(1)).findAll();
    }

    @Test
    public void testUpdateDipSuccess() {
        when(dipRepository.findById(1L)).thenReturn(Optional.of(dipEntity));
        when(dipRepository.save(any(DipEntity.class))).thenReturn(dipEntity);

        DipDTO updatedDTO = new DipDTO(1L, "Updated Dip", "New description", "SP", "São Paulo", new BigDecimal("25.0"), AccessTypeEnum.PUBLIC, "New location");
        DipDTO result = dipService.update(1L, updatedDTO);

        assertNotNull(result);
        assertEquals(updatedDTO.name(), result.name());
        assertEquals(updatedDTO.description(), result.description());

        verify(dipRepository, times(1)).findById(1L);
        verify(dipRepository, times(1)).save(any(DipEntity.class));
        // A validação ocorre no construtor de ValidationService, então não precisa de verify aqui
    }

    @Test
    public void testUpdateDipNotFound() {
        when(dipRepository.findById(1L)).thenReturn(Optional.empty());

        DipDTO updatedDTO = new DipDTO(1L, "Updated Dip", "New description", "SP", "São Paulo", new BigDecimal("25.0"), AccessTypeEnum.PUBLIC, "New location");

        Exception exception = assertThrows(DipNotFoundException.class, () -> {
            dipService.update(1L, updatedDTO);
        });

        String expectedMessage = "Dip com ID 1 não encontrado";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

        verify(dipRepository, times(1)).findById(1L);
        verify(dipRepository, never()).save(any(DipEntity.class));
    }
}
