package com.dip.danielaraujo.dip_project.dtos;

import com.dip.danielaraujo.dip_project.entities.OwnerEntity;

import java.util.UUID;

public record OwnerDTO(UUID id, String name, String contact) {
    public OwnerDTO(OwnerEntity owner){
        this(owner.getId(), owner.getName(), owner.getContact());
    }

    public static OwnerDTO fromEntity(OwnerEntity owner) {
        return new OwnerDTO(owner);
    }
}
