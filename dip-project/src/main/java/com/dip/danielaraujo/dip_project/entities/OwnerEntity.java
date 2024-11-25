package com.dip.danielaraujo.dip_project.entities;

import com.dip.danielaraujo.dip_project.dtos.OwnerDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "owner")
@Getter
@Setter
@NoArgsConstructor
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
}
