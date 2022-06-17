package com.example.lospollos.services;

import com.example.lospollos.dtos.HouseRequestDto;
import com.example.lospollos.exceptions.RecordNotFoundException;
import com.example.lospollos.models.House;
import com.example.lospollos.repositories.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HouseService {

    HouseRepository houseRepository;

    @Autowired
    public HouseService(HouseRepository houseRepository){
        this.houseRepository = houseRepository;
    }

    public Iterable<House> getAllHouses(){
        return houseRepository.findAll();
    }

    public House getHouse(long id){

        Optional<House> optionalHouse = houseRepository.findById(id);

        if(optionalHouse.isPresent()){
            return optionalHouse.get();
        } else {
            throw new RecordNotFoundException("This ID does not exist");
        }

    }

    public void deleteHouse(long id){
        if(houseRepository.existsById(id)){
            houseRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("This ID does not exist!");
        }

    }

    public long addHouse(HouseRequestDto houseRequestDto){
        House house = new House();
        house.setName(houseRequestDto.getName());
        house.setTown(houseRequestDto.getTown());

        House newHouse = houseRepository.save(house);
        long newId = newHouse.getId();
        return newId;
    }
}
