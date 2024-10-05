package com.dip.danielaraujo.dip_project.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "authentication")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationEntity {
    @Id
    private String email;
    private String password;

    @OneToOne
    @JoinColumn(name = "cliente_id")
    private ClientEntity client;
}
