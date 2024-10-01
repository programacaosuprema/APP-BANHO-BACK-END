package com.dip.danielaraujo.dip_project.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "authentication")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationEntity {
    @Id
    private String user;
    private String password;

    @OneToMany(mappedBy = "authentication")
    private List<ClientEntity> clients;
}
