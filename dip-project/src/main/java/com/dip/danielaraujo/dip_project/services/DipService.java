package com.dip.danielaraujo.dip_project.services;

import com.dip.danielaraujo.dip_project.dtos.DipDTO;
import com.dip.danielaraujo.dip_project.entities.DipEntity;
import com.dip.danielaraujo.dip_project.enums.AccessTypeEnum;
import com.dip.danielaraujo.dip_project.exceptions.DipNotFoundException;
import com.dip.danielaraujo.dip_project.repositories.DipRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DipService {

    @Autowired
    private DipRepository dipRepository;

    public DipDTO create(DipDTO dipDTO) {

        DipEntity dip = new DipEntity(dipDTO);

        DipEntity savedDip = dipRepository.save(dip);

        return new DipDTO(savedDip);
    }

    @Transactional
    public DipDTO findById(UUID id) {
        DipEntity dip = dipRepository.findById(id)
                .orElseThrow(() -> new DipNotFoundException("Dip com ID " + id + " não encontrado"));

        return new DipDTO(dip);
    }

    public List<DipDTO> findByName(String name) {
        List<DipEntity> dips = dipRepository.findByNameContainingIgnoreCase(name);
        return dips.stream().map(DipDTO::new).collect(Collectors.toList());
    }

    public List<DipDTO> findAll() {
        List<DipEntity> dips = dipRepository.findAll();
        return dips.stream().map(DipDTO::new).collect(Collectors.toList());
    }

    public DipDTO update(UUID id, DipDTO dipDTO) {
        DipEntity existingDip = dipRepository.findById(id)
                .orElseThrow(() -> new DipNotFoundException("Dip com ID " + id + " não encontrado"));

        existingDip.setName(dipDTO.name());
        existingDip.setDescription(dipDTO.description());
        existingDip.setState(dipDTO.state());
        existingDip.setCity(dipDTO.city());
        existingDip.setTemperature(dipDTO.temperature());

        if (dipDTO.access().equals("PRIVADO")){
            existingDip.setAccess(AccessTypeEnum.PRIVATE);
        }else{
            existingDip.setAccess(AccessTypeEnum.PRIVATE);
        }

        existingDip.setLocation(dipDTO.location());

        DipEntity updatedDip = dipRepository.save(existingDip);

        return new DipDTO(updatedDip);
    }
}
