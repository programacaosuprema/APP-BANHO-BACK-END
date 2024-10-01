package com.dip.danielaraujo.dip_project.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "user_rating")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRatingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;

    private double numberOfStars;
    private int assessmentAmount;

    @ManyToMany(mappedBy = "userRatings")
    private List<ClientEntity> clients;

    @ManyToMany(mappedBy = "userRatings")
    private List<DipEntity> dips;
}
