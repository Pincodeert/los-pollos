package com.example.lospollos.dtos;

import com.example.lospollos.models.Person;

import java.util.ArrayList;
import java.util.List;

public class HouseRequestDto {

    private String name;
    private String town;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getTown(){
        return town;
    }

    public void setTown(String town){
        this.town = town;
    }


}
