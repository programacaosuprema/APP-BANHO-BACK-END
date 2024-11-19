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

    @OneToOne(mappedBy = "image")
    private ClientEntity client;

    public ImageClientEntity(ImageClientDTO imageDTO) {
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

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

    public String getFileType() {
        return fileType;
    }
}
