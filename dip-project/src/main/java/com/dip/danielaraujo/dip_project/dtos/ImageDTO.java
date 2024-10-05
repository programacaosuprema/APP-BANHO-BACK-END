package com.dip.danielaraujo.dip_project.dtos;

import com.dip.danielaraujo.dip_project.entities.ImageEntity;

public record ImageDTO(Long id, String name, String src) {

    public ImageDTO(ImageEntity image){
        this(image.getId(), image.getName(), image.getSrc());
    }

    public static ImageDTO fromEntity(ImageEntity imageEntity) {
        return new ImageDTO(imageEntity);
    }
}
