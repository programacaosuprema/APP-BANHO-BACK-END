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

    public AuthenticationEntity(String email, String password, ClientEntity client) {
        this.email = email;
        this.password = password;
        this.client = client;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }
}
