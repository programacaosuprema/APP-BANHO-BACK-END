package com.dip.danielaraujo.dip_project.entities;

import com.dip.danielaraujo.dip_project.dtos.ImageClientDTO;
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
public class ImageClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String src;
    @Column(name = "file_type")
    @Enumerated(EnumType.STRING)
    private FileTypeEnum fileType;

    @OneToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private ClientEntity client;

    public ImageClientEntity(ImageClientDTO imageDTO, ClientEntity client) {
        this.name = imageDTO.name();
        this.src = imageDTO.src();
        try {
            this.fileType = FileTypeEnum.valueOf(imageDTO.filetype().toUpperCase());
        }catch (Exception e){
            this.fileType = null;
        }

        this.client = client;
    }
}
