package com.dip.danielaraujo.dip_project.repositories;

import com.dip.danielaraujo.dip_project.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    Optional<ClientEntity> findClientByFirstName(String firstName);
    boolean existsByEmail(String email);
}
