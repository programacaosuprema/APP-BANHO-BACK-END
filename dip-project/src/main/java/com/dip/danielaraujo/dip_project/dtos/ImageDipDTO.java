package com.dip.danielaraujo.dip_project.dtos;

import com.dip.danielaraujo.dip_project.entities.ImageDipEntity;

import java.util.UUID;

public record ImageDipDTO(UUID id, String name, String src, String filetype) {

    public ImageDipDTO(ImageDipEntity image){
        this(image.getId(), image.getName(), image.getSrc(), image.getFileType());
    }

    public static ImageDipDTO fromEntity(ImageDipEntity imageEntity) {
        return new ImageDipDTO(imageEntity);
    }
}
