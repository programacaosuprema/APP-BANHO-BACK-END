package com.dip.danielaraujo.dip_project.entities;

import com.dip.danielaraujo.dip_project.dtos.ImageDipDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "image_dip")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageDipEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String src;
    @Column(name = "file_type")
    private String fileType;

    @ManyToOne
    @JoinColumn(name = "dip_id")
    private DipEntity dip;

    public ImageDipEntity(ImageDipDTO imageDTO) {
        if (imageDTO != null) {
            this.name = imageDTO.name();
            this.src = imageDTO.src();
        } else {
            this.name = null;
            this.src = null;
        }
    }
}
