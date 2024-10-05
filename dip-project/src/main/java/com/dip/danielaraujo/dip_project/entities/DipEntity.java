package com.dip.danielaraujo.dip_project.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "dip")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DipEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String state;
    private String city;
    private double temperature;
    @Enumerated(EnumType.STRING)
    private AccessType access; // "PRIVADO" ou "PUBLICO"
    private String location;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private OwnerEntity owner;

    @OneToMany(mappedBy = "dip", cascade = CascadeType.ALL)
    private List<UserRatingEntity> userRatings; // Relacionamento OneToMany com UserRatingEntity
}
