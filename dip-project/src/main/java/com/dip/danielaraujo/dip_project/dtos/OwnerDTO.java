package com.dip.danielaraujo.dip_project.dtos;

import com.dip.danielaraujo.dip_project.entities.OwnerEntity;

public record OwnerDTO(Long id, String name, String contact) {
    public OwnerDTO(OwnerEntity owner){
        this(owner.getId(), owner.getName(), owner.getContact());
    }

    public static OwnerDTO fromEntity(OwnerEntity owner) {
        return new OwnerDTO(owner);
    }
}
