package com.dip.danielaraujo.dip_project.entities;

import com.dip.danielaraujo.dip_project.dtos.ClientDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "client")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String firstName;
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    private String phoneNumber;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
    private ImageClientEntity image = null;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
    private AuthenticationEntity authentication;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<UserRatingEntity> userRatings;

    public ClientEntity(ClientDTO clientDTO){
        this.firstName = clientDTO.firstName();
        this.lastName = clientDTO.lastName();
        this.email = clientDTO.email();
        this.phoneNumber = clientDTO.phoneNumber();
        if (clientDTO.image() != null) {
            this.image = new ImageClientEntity(clientDTO.image(), this);
        }
        this.authentication = new AuthenticationEntity(clientDTO.email(), clientDTO.password(), this);
    }
}
