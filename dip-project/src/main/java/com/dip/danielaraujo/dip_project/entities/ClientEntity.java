package com.dip.danielaraujo.dip_project.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "client")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;

    private String name;
    private String surname;
    private String email;
    private String phoneNumber;

    @OneToOne
    @JoinColumn(name = "image_id")
    private ImageEntity image;

    @ManyToOne
    @JoinColumn(name = "authentication_user", referencedColumnName = "user")
    private AuthenticationEntity authentication;

    @ManyToMany
    @JoinTable(
            name = "client_user_rating",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "user_rating_id")
    )
    private List<UserRatingEntity> userRatings;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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
