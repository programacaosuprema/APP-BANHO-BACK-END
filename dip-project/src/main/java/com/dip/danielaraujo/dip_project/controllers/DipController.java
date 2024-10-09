package com.dip.danielaraujo.dip_project.controllers;

import com.dip.danielaraujo.dip_project.dtos.DipDTO;
import com.dip.danielaraujo.dip_project.services.DipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dips")
public class DipController {

    @Autowired
    private DipService dipService;

    @PostMapping
    public ResponseEntity<DipDTO> createDip(@RequestBody DipDTO dipDTO) {
        DipDTO newDip = dipService.create(dipDTO);
        return new ResponseEntity<>(newDip, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DipDTO> getDipById(@PathVariable Long id) {
        DipDTO dip = dipService.findById(id);
        return ResponseEntity.ok(dip);
    }

    @GetMapping("/search")
    public ResponseEntity<List<DipDTO>> getDipsByName(@RequestParam String name) {
        List<DipDTO> dips = dipService.findByName(name);
        return ResponseEntity.ok(dips);
    }

    @GetMapping
    public ResponseEntity<List<DipDTO>> getAllDips() {
        List<DipDTO> dips = dipService.findAll();
        return ResponseEntity.ok(dips);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DipDTO> updateDip(@PathVariable Long id, @RequestBody DipDTO dipDTO) {
        DipDTO updatedDip = dipService.update(id, dipDTO);
        return ResponseEntity.ok(updatedDip);
    }
}
