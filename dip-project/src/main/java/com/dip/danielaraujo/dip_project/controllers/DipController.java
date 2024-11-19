package com.dip.danielaraujo.dip_project.controllers;

import com.dip.danielaraujo.dip_project.dtos.DipDTO;
import com.dip.danielaraujo.dip_project.services.DipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/dips")
@CrossOrigin("*")
public class DipController {

    @Autowired
    private DipService dipService;

    @PostMapping
    public ResponseEntity<?> createDip(@RequestBody DipDTO dipDTO) {
        try {
            DipDTO newDip = dipService.create(dipDTO);
            return new ResponseEntity<>(newDip, HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDipById(@PathVariable UUID id) {
        try{
            DipDTO dip = dipService.findById(id);
            return new ResponseEntity<>(dip, HttpStatus.FOUND);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping()
    public ResponseEntity<?> getDipsByName(@RequestParam String name) {
        try {
            List<DipDTO> dips = dipService.findByName(name);

            return ResponseEntity.ok(dips);

        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> findAll() {
        try {
            List<DipDTO> dips = dipService.findAll();
            return ResponseEntity.ok(dips);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDip(@PathVariable UUID id, @RequestBody DipDTO dipDTO) {
        try {
            DipDTO updatedDip = dipService.update(id, dipDTO);
            return ResponseEntity.ok(updatedDip);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
