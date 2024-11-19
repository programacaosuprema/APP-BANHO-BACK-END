package com.dip.danielaraujo.dip_project.entities;

import com.dip.danielaraujo.dip_project.dtos.OwnerDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "owner")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String contact;

    @OneToMany(mappedBy = "owner")
    private List<DipEntity> dips;

    public OwnerEntity (OwnerDTO ownerDTO) {
        this.name = ownerDTO.name();
        this.contact = ownerDTO.contact();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public List<DipEntity> getDips() {
        return dips;
    }

    public void setDips(List<DipEntity> dips) {
        this.dips = dips;
    }
}
