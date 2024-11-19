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

    public ImageDipEntity(ImageDipDTO imageDTO){
        if (imageDTO != null) {
            this.name = imageDTO.name();
            this.src = imageDTO.src();
        } else {
            this.name = null;
            this.src = null;
        }
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getFileType() {
        return fileType;
    }

    public DipEntity getDip() {
        return dip;
    }
}
