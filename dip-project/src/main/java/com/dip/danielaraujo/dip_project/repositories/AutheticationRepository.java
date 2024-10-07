package com.dip.danielaraujo.dip_project.repositories;


import com.dip.danielaraujo.dip_project.entities.AuthenticationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutheticationRepository extends JpaRepository<AuthenticationEntity, String> {
}
