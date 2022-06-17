package com.example.lospollos.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HoiController {

    @GetMapping("/hoi")
    public String sayHi(){
        return "Hoi!";
    }

    @GetMapping("/doei!")
    public String sayGoodbye(){
        return "doei!";
    }
}
