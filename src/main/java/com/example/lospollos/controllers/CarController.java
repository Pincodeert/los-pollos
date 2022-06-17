package com.example.lospollos.controllers;

import com.example.lospollos.dtos.CarRequestDto;
import com.example.lospollos.models.Car;
import com.example.lospollos.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService){
        this.carService = carService;
    }

    @GetMapping("/cars")
    public ResponseEntity<Object> getAllCars(){
        return ResponseEntity.ok(carService.getAllCars());
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<Object> getCar(@PathVariable ("id") long id){
        return ResponseEntity.ok(carService.getCar(id));
    }

    @DeleteMapping("/cars/{id}")
    public ResponseEntity<Object> deleteCar(@PathVariable ("id") long id){
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/cars")
    public ResponseEntity<Object> addCar(@Valid @RequestBody CarRequestDto carRequestDto){
        long newId = carService.addCar(carRequestDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).build();
    }

}
