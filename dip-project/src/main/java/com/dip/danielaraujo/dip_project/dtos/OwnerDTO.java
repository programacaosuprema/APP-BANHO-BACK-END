package com.dip.danielaraujo.dip_project.dtos;

import com.dip.danielaraujo.dip_project.entities.OwnerEntity;

public record OwnerDTO(String name, String contact) {
    public static OwnerDTO fromEntity(OwnerEntity owner) {
        return new OwnerDTO(owner.getName(), owner.getContact());
    }

    public static OwnerEntity toEntity(OwnerDTO ownerDTO) {
        OwnerEntity owner = new OwnerEntity();
        owner.setName(ownerDTO.name);
        owner.setContact(ownerDTO.contact);
        return owner;
    }
}
