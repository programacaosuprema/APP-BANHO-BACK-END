package com.dip.danielaraujo.dip_project.controllers;

import com.dip.danielaraujo.dip_project.dtos.AuthenticationDTO;
import com.dip.danielaraujo.dip_project.dtos.LoginResponseDTO;
import com.dip.danielaraujo.dip_project.dtos.RegisterDTO;
import com.dip.danielaraujo.dip_project.entities.UserEntity;
import com.dip.danielaraujo.dip_project.infra.security.TokenService;
import com.dip.danielaraujo.dip_project.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO data) {
        //criptografia
        var userNamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());

        //autenticação

        var auth = this.manager.authenticate(userNamePassword);

        var token = this.tokenService.generateToken((UserEntity) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO data) {
        if (this.repository.findByLogin(data.login()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        UserEntity newuser = new UserEntity(data.login(), encryptedPassword, data.role());

        this.repository.save(newuser);

        return ResponseEntity.ok().build();
    }

}
