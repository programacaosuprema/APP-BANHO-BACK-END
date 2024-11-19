package com.dip.danielaraujo.dip_project.dtos;

import com.dip.danielaraujo.dip_project.entities.ImageClientEntity;

import java.util.UUID;

public record ImageClientDTO(UUID id, String name, String src, String filetype) {

    public ImageClientDTO(ImageClientEntity image){
        this(image.getId(), image.getName(), image.getSrc(), image.getFileType());
    }

    public static ImageClientDTO fromEntity(ImageClientEntity imageEntity) {
        return new ImageClientDTO(imageEntity);
    }
}
