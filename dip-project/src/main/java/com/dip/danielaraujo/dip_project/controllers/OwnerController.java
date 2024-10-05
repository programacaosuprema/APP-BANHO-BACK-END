package com.dip.danielaraujo.dip_project.controllers;
import com.dip.danielaraujo.dip_project.dtos.OwnerDTO;
import com.dip.danielaraujo.dip_project.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owners")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @PostMapping()
    public OwnerDTO create(@RequestBody OwnerDTO owner){
        return ownerService.createOwner(owner);
    }

    @GetMapping()
    public List<OwnerDTO> findAll(){
        return ownerService.getAllOwners();
    }

}
