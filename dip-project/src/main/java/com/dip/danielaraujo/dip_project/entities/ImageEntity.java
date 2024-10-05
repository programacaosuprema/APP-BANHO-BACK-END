package com.dip.danielaraujo.dip_project.entities;

import com.dip.danielaraujo.dip_project.dtos.ImageDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "image")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String src;

    @OneToOne(mappedBy = "image")
    private ClientEntity client;

    public ImageEntity(ImageDTO imageDTO){
        this.name = imageDTO.name();
        this.src = imageDTO.src();
    }
}
