package com.dip.danielaraujo.dip_project.entities;

import com.dip.danielaraujo.dip_project.dtos.ClientDTO;
import jakarta.persistence.*;
import lombok.*;

import java.awt.*;
import java.util.List;

@Entity
@Table(name = "client")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    @OneToOne
    @JoinColumn(name = "image_id")
    private ImageEntity image;

    @OneToOne(mappedBy = "client")
    private AuthenticationEntity authentication;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<UserRatingEntity> userRatings;

    public ClientEntity(ClientDTO clientDTO){
        this.firstName = clientDTO.firstName();
        this.lastName = clientDTO.lastName();
        this.email = clientDTO.email();
        this.phoneNumber = clientDTO.phoneNumber();
        this.image =  new ImageEntity(clientDTO.image());
    }
}
