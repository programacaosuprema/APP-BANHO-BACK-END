package com.dip.danielaraujo.dip_project.dtos;

import com.dip.danielaraujo.dip_project.entities.DipEntity;
import com.dip.danielaraujo.dip_project.entities.ImageEntity;
import jakarta.persistence.AccessType;

import java.math.BigDecimal;

public record DipDTO(Long id, String name, String description, String state,
                     String city, BigDecimal tempeture, AccessType access, String location) {
    public DipDTO(DipEntity dip){
        this(dip.getId(), dip.getName(), dip.getDescription(),
                dip.getState(), dip.getCity(), dip.getTemperature(), dip.getAccess(), dip.getLocation());
    }

    public static DipDTO fromEntity(DipEntity dip) {
        return new DipDTO(dip);
    }
}
