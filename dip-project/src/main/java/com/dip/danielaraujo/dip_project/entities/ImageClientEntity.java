package com.dip.danielaraujo.dip_project.entities;

import com.dip.danielaraujo.dip_project.dtos.ImageClientDTO;
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
public class ImageClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String src;
    @Column(name = "file_type")
    private String fileType;

    @OneToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private ClientEntity client;

    public ImageClientEntity(ImageClientDTO imageDTO, ClientEntity client) {
        if (imageDTO != null) {
            this.name = imageDTO.name();
            this.src = imageDTO.src();
            this.fileType = imageDTO.filetype();
        } else {
            this.name = null;
            this.src = null;
            this.fileType = null;
        }
        this.client = client;
    }
}
