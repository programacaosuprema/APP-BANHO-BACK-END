package com.dip.danielaraujo.dip_project.repositories;

import com.dip.danielaraujo.dip_project.entities.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    UserDetails findByLogin(String login);

    /*@Transactional
    @Query("UPDATE UserEntity u SET u.login = :login WHERE u.email = :email")
    int updateUserLogin(String login, String email);*/

}
