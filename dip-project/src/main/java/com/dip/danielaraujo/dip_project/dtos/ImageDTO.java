package com.dip.danielaraujo.dip_project.dtos;

import com.dip.danielaraujo.dip_project.entities.ImageEntity;

public record ImageDTO(Long id, String name, String src) {
    public static ImageDTO fromEntity(ImageEntity imageEntity) {
        return new ImageDTO(imageEntity.getId(), imageEntity.getName(), imageEntity.getSrc());
    }

    public static ImageEntity toEntity(ImageDTO imageDTO) {
        ImageEntity image = new ImageEntity();
        image.setId(imageDTO.id);
        image.setName(imageDTO.name);
        image.setSrc(imageDTO.src);
        return image;
    }
}
