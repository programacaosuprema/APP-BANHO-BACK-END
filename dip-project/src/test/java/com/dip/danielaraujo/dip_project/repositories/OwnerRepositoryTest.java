package com.dip.danielaraujo.dip_project.repositories;

import com.dip.danielaraujo.dip_project.dtos.OwnerDTO;
import com.dip.danielaraujo.dip_project.entities.OwnerEntity;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

@DataJpaTest
class OwnerRepositoryTest {
    @Autowired
    private EntityManager manager;

    @Autowired
    private OwnerRepository ownerRepository;

    @Test
    @DisplayName("Shold get owner sucessfully from data base")
    public void createOwnerSuccess(){
        String name = "Daniel";
        OwnerDTO ownerDTO = new OwnerDTO(1L, "Daniel", "98989060439");

        this.createOwner(ownerDTO);

        Optional<OwnerEntity> result = this.ownerRepository.findOwnerByName(name);

        assertThat(result.isPresent()).isTrue();
        assertEquals(1, result.stream().toList().size());
    }

    public void createOwner(OwnerDTO ownerDTO){
        OwnerEntity newOwner = new OwnerEntity(ownerDTO);
        this.manager.persist(newOwner);
        this.manager.flush();
    }

}