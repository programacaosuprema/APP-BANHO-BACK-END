package com.dip.danielaraujo.dip_project.dtos;

import com.dip.danielaraujo.dip_project.entities.DipEntity;
import com.dip.danielaraujo.dip_project.enums.AccessTypeEnum;

import java.math.BigDecimal;
import java.util.List;

public record DipDTO(Long id, String name, String description, String state,
                     String city, BigDecimal temperature, AccessTypeEnum access, String location, List<ImageDipDTO> images) {
    public DipDTO(DipEntity dip){
        this(dip.getId(), dip.getName(), dip.getDescription(),
                dip.getState(), dip.getCity(), dip.getTemperature(), dip.getAccess(), dip.getLocation(), dip.getImages().stream().map(ImageDipDTO::new).toList());
    }

    public static DipDTO fromEntity(DipEntity dip) {
        return new DipDTO(dip);
    }
}
