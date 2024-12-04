package com.dip.danielaraujo.dip_project.dtos;

import com.dip.danielaraujo.dip_project.entities.ImageClientEntity;

import java.util.UUID;

public record ImageClientDTO(UUID id, String name, String src, String filetype) {

    public ImageClientDTO(ImageClientEntity image) {
        this(
                image != null ? image.getId() : null,
                image != null ? image.getName() : null,
                image != null ? image.getSrc() : null,
                image != null && image.getFileType() != null ? String.valueOf(image.getFileType()) : null
        );
    }

    public static ImageClientDTO fromEntity(ImageClientEntity imageEntity) {
        if (imageEntity == null) {
            return new ImageClientDTO(null, null, null, null);
        }
        return new ImageClientDTO(imageEntity);
    }
}
