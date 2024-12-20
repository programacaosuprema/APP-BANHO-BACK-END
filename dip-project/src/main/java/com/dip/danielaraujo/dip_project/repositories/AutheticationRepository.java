package com.dip.danielaraujo.dip_project.repositories;


import com.dip.danielaraujo.dip_project.entities.AuthenticationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AutheticationRepository extends JpaRepository<AuthenticationEntity, UUID> {
    AuthenticationEntity findByEmail(String email);

    @Query("SELECT a FROM AuthenticationEntity a WHERE a.email = :email AND a.password = :password")
    AuthenticationEntity findByEmailAndPassword(String email, String password);
}
