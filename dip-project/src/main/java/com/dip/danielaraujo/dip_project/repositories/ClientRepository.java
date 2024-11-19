package com.dip.danielaraujo.dip_project.repositories;

import com.dip.danielaraujo.dip_project.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, UUID> {
    List<ClientEntity> findByFirstName(String firstName);

    boolean existsByEmail(String email);
}
