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

    @OneToMany(mappedBy = "dip", cascade = CascadeType.ALL)
    private List<ImageDipEntity> images;

    public DipEntity(DipDTO dip){
        this.name = dip.name();
            this.description = dip.description();
        this.state = dip.state();
        this.city = dip.city();
        this.temperature = dip.temperature();
        this.access = dip.access();
        this.location = dip.location();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public BigDecimal getTemperature() {
        return temperature;
    }

    public void setTemperature(BigDecimal temperature) {
        this.temperature = temperature;
    }

    public AccessTypeEnum getAccess() {
        return access;
    }

    public void setAccess(AccessTypeEnum access) {
        this.access = access;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public OwnerEntity getOwner() {
        return owner;
    }

    public void setOwner(OwnerEntity owner) {
        this.owner = owner;
    }

    public List<UserRatingEntity> getUserRatings() {
        return userRatings;
    }

    public List<ImageDipEntity> getImages() {
        return images;
    }
}
