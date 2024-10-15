package com.dip.danielaraujo.dip_project.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "authentication")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationEntity {

    @Id
    @Column(name = "email", nullable = false)
    private String email;
    private String password;

    @OneToOne
    @MapsId
    @JoinColumn(name = "email", referencedColumnName = "email")
    private ClientEntity client;
}
