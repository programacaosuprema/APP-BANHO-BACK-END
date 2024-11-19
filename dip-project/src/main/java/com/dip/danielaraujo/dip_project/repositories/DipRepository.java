package com.dip.danielaraujo.dip_project.repositories;

import com.dip.danielaraujo.dip_project.entities.DipEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DipRepository extends JpaRepository<DipEntity, UUID> {
    List<DipEntity> findByNameContainingIgnoreCase(String name);
}
