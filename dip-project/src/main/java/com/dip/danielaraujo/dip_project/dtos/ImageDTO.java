package com.dip.danielaraujo.dip_project.dtos;

import com.dip.danielaraujo.dip_project.entities.ImageEntity;
import com.dip.danielaraujo.dip_project.validation.image.ValidFileType;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ImageDTO(UUID id,
                       @NotNull(message = "O nome não pode ser nulo!")
                       String name,
                       @NotNull(message = "O src não pode ser nulo!")
                       String src,
                       @NotNull(message = "O tipo do arquivo não pode ser nulo!")
                       @ValidFileType(message = "O tipo de arquivo deve ser 'PNG' ou 'JPG'")
                       String filetype){
    public ImageDTO(ImageEntity image){
        this(image.getId(), image.getName(), image.getSrc(), image.getFileType());
    }

    public static ImageDTO fromEntity(ImageEntity imageEntity) {
        return new ImageDTO(imageEntity);
    }
}
