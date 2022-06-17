package com.example.lospollos.dtos;

import javax.validation.constraints.Size;

public class CarRequestDto {

    @Size(min = 3, max = 20)
    private String brand;

    @Size(min = 3, max = 20)
    private String type;

    public String getBrand(){
        return brand;
    }

    public void setBrand(String brand){
        this.brand = brand;
    }

    public String getType(){
        return  type;
    }

    public void setType(){
        this.type = type;
    }
}
