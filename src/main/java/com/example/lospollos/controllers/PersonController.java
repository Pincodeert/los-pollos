package com.example.lospollos.controllers;

import com.example.lospollos.dtos.PersonRequestDto;
import com.example.lospollos.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService){
        this.personService = personService;
    }


    @GetMapping("/persons")
    public ResponseEntity<Object> getAllPersons(@RequestParam(name = "lastname", defaultValue = "") String lastName){
        return ResponseEntity.ok(personService.getAllPersons(lastName));
    }

    @GetMapping("/persons/{id}")
    public ResponseEntity<Object> getPerson(@PathVariable("id") long id){
        return ResponseEntity.ok(personService.getPerson(id));
    }

    @DeleteMapping("/persons/{id}")
    public ResponseEntity<Object> deletePerson(@PathVariable("id")long id){
        personService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/persons")
    public ResponseEntity<Object> addPerson(@Valid @RequestBody PersonRequestDto personRequestDto){
        long newId = personService.addPerson(personRequestDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/persons/{id}")
    public ResponseEntity<Object> updatePerson( @PathVariable ("id") long id, @Valid
                                               @RequestBody PersonRequestDto personRequestDto){
        personService.updatePerson(id, personRequestDto);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/persons/{id}")
    public ResponseEntity<Object> partialUpdatePerson( @PathVariable ("id") long id,
                                                      @RequestBody PersonRequestDto personRequestDto){
        personService.partialUpdatePerson(id, personRequestDto);
        return ResponseEntity.noContent().build();
    }

}
