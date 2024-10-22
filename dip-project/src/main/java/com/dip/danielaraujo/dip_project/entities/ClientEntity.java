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
    @Column(unique = true, nullable = false)
    private String email;
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.PERSIST, optional = false)
    @JoinColumn(name = "image_id")
    private ImageEntity image;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
    private AuthenticationEntity authentication;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<UserRatingEntity> userRatings;

    public ClientEntity(ClientDTO clientDTO){
        this.firstName = clientDTO.firstName();
        this.lastName = clientDTO.lastName();
        this.email = clientDTO.email();
        this.phoneNumber = clientDTO.phoneNumber();
        this.image =  new ImageEntity(clientDTO.image());
        this.authentication = new AuthenticationEntity(clientDTO.email(), clientDTO.password(), this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public ImageEntity getImage() {
        return image;
    }

    public void setImage(ImageEntity image) {
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
