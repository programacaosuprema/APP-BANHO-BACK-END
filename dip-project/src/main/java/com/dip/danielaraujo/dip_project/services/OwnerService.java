package com.dip.danielaraujo.dip_project.services;

import com.dip.danielaraujo.dip_project.dtos.OwnerDTO;
import com.dip.danielaraujo.dip_project.entities.OwnerEntity;
import com.dip.danielaraujo.dip_project.repositories.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OwnerService {
    @Autowired
    private OwnerRepository ownerRepository;

    public List<OwnerDTO> getAllOwners() {
        List<OwnerEntity> owners = ownerRepository.findAll();
        return owners.stream()
                .map(OwnerDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public OwnerDTO getOwnerById(UUID id) {
        return new OwnerDTO(ownerRepository.findById(id).orElseThrow(() -> new ExpressionException("Owner not found")));
    }

    public OwnerDTO createOwner(OwnerDTO owner) {
        return new OwnerDTO(ownerRepository.save(new OwnerEntity(owner)));
    }

    public OwnerDTO updateOwner(UUID id, OwnerDTO ownerDTO) {
        OwnerEntity owner =  new OwnerEntity(getOwnerById(id));
        owner.setName(ownerDTO.name());
        owner.setContact(ownerDTO.contact());
        return new OwnerDTO(ownerRepository.save(owner));
    }
}
