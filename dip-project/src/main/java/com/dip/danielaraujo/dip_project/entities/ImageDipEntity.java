package com.dip.danielaraujo.dip_project.entities;

import com.dip.danielaraujo.dip_project.dtos.ImageDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "image_dip")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageDipEntity extends ImageEntity {
    @ManyToOne
    @JoinColumn(name = "dip_id")
    private DipEntity dip;

    public ImageDipEntity(ImageDTO imageDTO, DipEntity dip) {
        super(null, imageDTO.name(), imageDTO.src(), imageDTO.filetype());
        this.dip = dip;
    }
}
