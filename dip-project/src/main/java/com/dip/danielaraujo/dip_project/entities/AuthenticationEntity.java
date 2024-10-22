package com.dip.danielaraujo.dip_project.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "authentication")
@Getter
@Setter
@NoArgsConstructor
public class AuthenticationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
