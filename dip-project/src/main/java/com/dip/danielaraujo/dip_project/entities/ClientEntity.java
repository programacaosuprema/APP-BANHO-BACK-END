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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private String firstName;
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.PERSIST, optional = false)
    @JoinColumn(name = "image_id")
    private ImageClientEntity image;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
    private AuthenticationEntity authentication;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<UserRatingEntity> userRatings;

    public ClientEntity(ClientDTO clientDTO){
        this.firstName = clientDTO.firstName();
        this.lastName = clientDTO.lastName();
        this.email = clientDTO.email();
        this.phoneNumber = clientDTO.phoneNumber();
        this.image =  new ImageClientEntity(clientDTO.image());
        this.authentication = new AuthenticationEntity(clientDTO.email(), clientDTO.password(), this);
    }

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ImageClientEntity getImage() {
        return image;
    }

    public void setImage(ImageClientEntity image) {
        this.image = image;
    }

    public AuthenticationEntity getAuthentication() {
        return authentication;
    }

    public void setAuthentication(AuthenticationEntity authentication) {
        this.authentication = authentication;
    }

    public List<UserRatingEntity> getUserRatings() {
        return userRatings;
    }

    public void setUserRatings(List<UserRatingEntity> userRatings) {
        this.userRatings = userRatings;
    }
}
