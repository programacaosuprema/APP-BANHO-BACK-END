package com.dip.danielaraujo.dip_project.entities;

import com.dip.danielaraujo.dip_project.dtos.ImageDTO;
import com.dip.danielaraujo.dip_project.enums.FileTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "image_client")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageClientEntity extends ImageEntity {
    @Enumerated(EnumType.STRING)
    private FileTypeEnum fileTypeEnum;

    @OneToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private ClientEntity client;

    public ImageClientEntity(ImageDTO imageDTO, ClientEntity client) {
        super(null, imageDTO.name(), imageDTO.src(), imageDTO.filetype());
        try {
            this.fileTypeEnum = FileTypeEnum.valueOf(imageDTO.filetype().toUpperCase());
        } catch (Exception e) {
            this.fileTypeEnum = null;
        }
        this.client = client;
    }
}
