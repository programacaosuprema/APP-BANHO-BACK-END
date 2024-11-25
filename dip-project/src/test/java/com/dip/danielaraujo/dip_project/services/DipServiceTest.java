package com.dip.danielaraujo.dip_project.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.dip.danielaraujo.dip_project.dtos.DipDTO;
import com.dip.danielaraujo.dip_project.dtos.ImageDipDTO;
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
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class DipServiceTest {

    @Mock
    private DipRepository dipRepository;

    @Mock
    private ValidationService validationService;

    @InjectMocks
    private DipService dipService;

    private final UUID id_image1 = UUID.randomUUID();
    private final UUID id_image2 = UUID.randomUUID();

    private DipDTO dipDTO;
    private DipEntity dipEntity;
    private UUID dipId;  // Agora o id é do tipo UUID

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Criando imagens
        ImageDipDTO image1 = new ImageDipDTO(id_image1, "dip1", "https://dip.com.br/src/images", "JPEG");
        ImageDipDTO image2 = new ImageDipDTO(id_image2, "dip2", "https://dip.com.br/src/images", "PNG");

        List<ImageDipDTO> images = new ArrayList<>() {{
            add(image1);
            add(image2);
        }};

        // Criando um UUID para o id
        dipId = UUID.randomUUID();

        // Instanciando objetos de teste
        this.dipDTO = new DipDTO(dipId, "Dip Test", "Beautiful dip", "MA", "São Luís", new BigDecimal("25.0"), "PRIVADO", "Some location", images);
        dipEntity = new DipEntity(dipDTO);
    }

    @Test
    public void testCreateDip() {
        when(dipRepository.save(any(DipEntity.class))).thenReturn(dipEntity);

        // Criando o dip usando o UUID
        DipDTO result = dipService.create(dipDTO);

        assertNotNull(result);
        assertEquals(dipDTO.name(), result.name());
        assertEquals(dipDTO.description(), result.description());

        verify(dipRepository, times(1)).save(any(DipEntity.class));
    }

    @Test
    public void testFindByIdSuccess() {
        // Aqui usamos UUID para encontrar o dip
        when(dipRepository.findById(any(UUID.class))).thenReturn(Optional.of(dipEntity));

        // Passando UUID no método findById
        DipDTO result = dipService.findById(dipId);

        assertNotNull(result);
        assertEquals(dipDTO.name(), result.name());
        assertEquals(dipDTO.description(), result.description());

        verify(dipRepository, times(1)).findById(any(UUID.class));
    }

    @Test
    public void testFindByIdNotFound() {
        when(dipRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        Exception exception = assertThrows(DipNotFoundException.class, () -> {
            dipService.findById(dipId);
        });

        String expectedMessage = "Dip com ID " + dipId + " não encontrado";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

        verify(dipRepository, times(1)).findById(any(UUID.class));
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
        when(dipRepository.findById(any(UUID.class))).thenReturn(Optional.of(dipEntity));
        when(dipRepository.save(any(DipEntity.class))).thenReturn(dipEntity);

        ImageDipDTO image1 = new ImageDipDTO(UUID.randomUUID(), "dip_apdate1", "https://dip.com.br/src/images", "PNG");
        ImageDipDTO image2 = new ImageDipDTO(UUID.randomUUID(), "dip_apdate2", "https://dip.com.br/src/images", "PNG");

        List<ImageDipDTO> images = new ArrayList<>() {{
            add(image1);
            add(image2);
        }};

        // Criando um novo DipDTO com o mesmo ID para atualização
        DipDTO updatedDTO = new DipDTO(dipId, "Updated Dip", "New description", "SP", "São Paulo", new BigDecimal("25.0"), "PÚBLICO", "New location", images);
        DipDTO result = dipService.update(dipId, updatedDTO);

        assertNotNull(result);
        assertEquals(updatedDTO.name(), result.name());
        assertEquals(updatedDTO.description(), result.description());

        verify(dipRepository, times(1)).findById(any(UUID.class));
        verify(dipRepository, times(1)).save(any(DipEntity.class));
    }

    @Test
    public void testUpdateDipNotFound() {
        when(dipRepository.findById(any(UUID.class))).thenReturn(Optional.empty());
        ImageDipDTO image1 = new ImageDipDTO(UUID.randomUUID(), "dip_apdate1", "https://dip.com.br/src/images", "PNG");
        ImageDipDTO image2 = new ImageDipDTO(UUID.randomUUID(), "dip_apdate2", "https://dip.com.br/src/images", "PNG");

        List<ImageDipDTO> images = new ArrayList<>() {{
            add(image1);
            add(image2);
        }};

        DipDTO updatedDTO = new DipDTO(dipId, "Updated Dip", "New description", "SP", "São Paulo", new BigDecimal("25.0"), "PÚBLICO", "New location", images);

        Exception exception = assertThrows(DipNotFoundException.class, () -> {
            dipService.update(dipId, updatedDTO);
        });

        String expectedMessage = "Dip com ID " + dipId + " não encontrado";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

        verify(dipRepository, times(1)).findById(any(UUID.class));
        verify(dipRepository, never()).save(any(DipEntity.class));
    }
}
