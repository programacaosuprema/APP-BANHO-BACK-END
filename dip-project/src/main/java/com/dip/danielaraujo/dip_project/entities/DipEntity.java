package com.dip.danielaraujo.dip_project.entities;

import com.dip.danielaraujo.dip_project.dtos.DipDTO;
import com.dip.danielaraujo.dip_project.dtos.ImageDipDTO;
import com.dip.danielaraujo.dip_project.enums.AccessTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Entity
@Table(name = "dip")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DipEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

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

    @OneToMany(mappedBy = "dip", cascade = CascadeType.ALL)
    private List<ImageDipEntity> images;

    public DipEntity(DipDTO dip){
        this.name = dip.name();
        this.description = dip.description();
        this.state = dip.state();
        this.city = dip.city();
        this.temperature = dip.temperature();
        this.access = stringToAccessTypeEnum(dip.access());
        this.location = dip.location();

        this.images = dip.images().stream().map(ImageDipEntity::new).toList();
    }

    private String accessTypeEnumToString(AccessTypeEnum access){
        if (access.equals(AccessTypeEnum.PUBLIC)) {
            return "PÚBLICO";
        } else {
            return "PRIVADO";
        }
    }

    private AccessTypeEnum stringToAccessTypeEnum(String access){
        if (access.equalsIgnoreCase("PRIVADO")){
            return AccessTypeEnum.PRIVATE;
        }else{
            return AccessTypeEnum.PUBLIC;
        }
    }

    public String getStringAccess(){
        return this.accessTypeEnumToString(this.access);
    }
}
