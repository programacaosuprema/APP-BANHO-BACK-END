package com.dip.danielaraujo.dip_project.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "authentication")
@Getter
@Setter
@NoArgsConstructor
public class AuthenticationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String email;
    private String password;

    @OneToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private ClientEntity client;

    public AuthenticationEntity(String email, String password, ClientEntity client) {
        this.email = email;
        this.password = password;
        this.client = client;
    }
}
