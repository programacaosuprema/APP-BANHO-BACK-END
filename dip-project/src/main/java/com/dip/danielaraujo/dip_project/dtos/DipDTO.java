package com.dip.danielaraujo.dip_project.dtos;

import com.dip.danielaraujo.dip_project.entities.DipEntity;
import com.dip.danielaraujo.dip_project.enums.AccessTypeEnum;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record DipDTO(UUID id, String name, String description, String state,
                     String city, BigDecimal temperature, String access, String location, List<ImageDipDTO> images) {
    public DipDTO(DipEntity dip){
        this(dip.getId(), dip.getName(), dip.getDescription(),
                dip.getState(), dip.getCity(), dip.getTemperature(), dip.getAccess(), dip.getLocation(), dip.getImages().stream().map(ImageDipDTO::new).toList());
    }

    public static DipDTO fromEntity(DipEntity dip) {
        return new DipDTO(dip);
    }
}
