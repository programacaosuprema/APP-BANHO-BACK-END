package com.dip.danielaraujo.dip_project.services;

import com.dip.danielaraujo.dip_project.dtos.OwnerDTO;
import com.dip.danielaraujo.dip_project.entities.OwnerEntity;
import com.dip.danielaraujo.dip_project.repositories.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OwnerService {
    @Autowired
    private OwnerRepository ownerRepository;

    public List<OwnerDTO> getAllOwners() {
        List<OwnerEntity> owners = ownerRepository.findAll();
        return owners.stream()
                .map(OwnerDTO::fromEntity) // Converte cada OwnerEntity para OwnerDTO
                .collect(Collectors.toList()); // Coleta os resultados em uma lista
    }


    public OwnerEntity getOwnerById(Long id) {
        return ownerRepository.findById(id).orElseThrow(() -> new ExpressionException("Owner not found"));
    }

    public OwnerDTO createOwner(OwnerDTO owner) {
        return new OwnerDTO(ownerRepository.save(new OwnerEntity(owner)));
    }

    public OwnerEntity updateOwner(Long id, OwnerEntity ownerDetails) {
        OwnerEntity owner = getOwnerById(id);
        owner.setName(ownerDetails.getName());
        owner.setContact(ownerDetails.getContact());
        return ownerRepository.save(owner);
    }

    public void deleteOwner(Long id) {
        OwnerEntity owner = getOwnerById(id);
        ownerRepository.delete(owner);
    }
}
