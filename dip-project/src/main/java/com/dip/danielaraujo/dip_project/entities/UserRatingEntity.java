package com.dip.danielaraujo.dip_project.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "user_rating")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRatingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientEntity client;

    @ManyToOne
    @JoinColumn(name = "dip_id")
    private DipEntity dip;

    private double numberOfStars;

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

    public DipEntity getDip() {
        return dip;
    }

    public void setDip(DipEntity dip) {
        this.dip = dip;
    }

    public double getNumberOfStars() {
        return numberOfStars;
    }

    public void setNumberOfStars(double numberOfStars) {
        this.numberOfStars = numberOfStars;
    }
}
