package com.dip.danielaraujo.dip_project.repositories;

import com.dip.danielaraujo.dip_project.entities.DipEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DipRepository extends JpaRepository<DipEntity, Long> {
    List<DipEntity> findByNameContainingIgnoreCase(String name);
}
