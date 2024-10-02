package com.dip.danielaraujo.dip_project.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_rating")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRatingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientEntity client;

    @ManyToOne
    @JoinColumn(name = "dip_id")
    private DipEntity dip;

    private double numberOfStars; // Atributo extra
}
