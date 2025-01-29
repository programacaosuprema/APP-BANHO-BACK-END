package com.dip.danielaraujo.dip_project.services;

import com.dip.danielaraujo.dip_project.entities.UserEntity;
import com.dip.danielaraujo.dip_project.enums.UserRole;
import com.dip.danielaraujo.dip_project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void register(String login, String password, UserRole role) {
        if (userRepository.findByLogin(login) != null) {
            throw new RuntimeException("Login já registrado");
        }

        String encryptedPassword = passwordEncoder.encode(password);
        UserEntity newUser = new UserEntity(login, encryptedPassword, role);

        userRepository.save(newUser);
    }
}
