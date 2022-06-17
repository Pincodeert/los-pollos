package com.example.lospollos.controllers;

import com.example.lospollos.dtos.HouseRequestDto;
import com.example.lospollos.models.House;
import com.example.lospollos.services.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class HouseController {

    HouseService houseService;

    @Autowired
    public HouseController(HouseService houseService){
        this.houseService = houseService;
    }

    @GetMapping("/houses")
    public ResponseEntity<Object> getAllHouses(){
        return ResponseEntity.ok(houseService.getAllHouses());
    }

    @GetMapping("/houses/{id}")
    public ResponseEntity<Object> getHouse(@PathVariable ("id") long id){
        return ResponseEntity.ok(houseService.getHouse(id));
    }

    @DeleteMapping("/houses/{id}")
    public ResponseEntity<Object> deleteHouse(@PathVariable ("id") long id){
        houseService.deleteHouse(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/houses")
    public ResponseEntity<Object> addHouse(@RequestBody HouseRequestDto houseRequestDto){
        long newId = houseService.addHouse(houseRequestDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newId).toUri();
        return ResponseEntity.created(location).build();
    }




}
