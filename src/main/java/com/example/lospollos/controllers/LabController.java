package com.example.lospollos.controllers;

import com.example.lospollos.dtos.LabRequestDto;
import com.example.lospollos.models.Lab;
import com.example.lospollos.services.LabService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class LabController {

    private final LabService labService;

    public LabController(LabService labService){
        this.labService = labService;
    }

    @GetMapping("/labs")
    public ResponseEntity<Object> getAllLabs(){
        return ResponseEntity.ok(labService.getAllLabs());
    }

    @GetMapping("/labs/{id}")
    public ResponseEntity<Object> getLab(@PathVariable("id") long id){
        return ResponseEntity.ok(labService.getLab(id));
    }

    @DeleteMapping("/labs/{id}")
    public ResponseEntity<Object> deleteLab(@PathVariable("id") long id){
        labService.deleteLab(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/labs")
    public ResponseEntity<Object> addLab(@RequestBody LabRequestDto labRequestDto){
        long newId = labService.addLab(labRequestDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/labs/{id}")
    public ResponseEntity<Object> updateLab(@PathVariable("id") long id, @RequestBody Lab lab){
        labService.updateLab(id, lab);
        return ResponseEntity.noContent().build();
    }
}
