package com.dip.danielaraujo.dip_project.entities;

import com.dip.danielaraujo.dip_project.dtos.DipDTO;
import com.dip.danielaraujo.dip_project.enums.AccessTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
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
    private BigDecimal temperature;
    @Enumerated(EnumType.STRING)
    private AccessTypeEnum access; // "PRIVATE" or "PUBLIC"
    private String location;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private OwnerEntity owner;

    @OneToMany(mappedBy = "dip", cascade = CascadeType.ALL)
    private List<UserRatingEntity> userRatings;

    public DipEntity(DipDTO dip){
        this.name = dip.name();
        this.description = dip.description();
        this.state = dip.state();
        this.city = dip.city();
        this.temperature = dip.temperature();
        this.access = dip.access();
        this.location = dip.location();
    }
}
