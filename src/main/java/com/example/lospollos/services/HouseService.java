package com.example.lospollos.services;

import com.example.lospollos.dtos.HouseRequestDto;
import com.example.lospollos.exceptions.RecordNotFoundException;
import com.example.lospollos.models.House;
import com.example.lospollos.models.Person;
import com.example.lospollos.repositories.HouseRepository;
import com.example.lospollos.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HouseService {

    HouseRepository houseRepository;
    PersonRepository personRepository;

    @Autowired
    public HouseService(HouseRepository houseRepository, PersonRepository personRepository){
        this.houseRepository = houseRepository;
        this.personRepository = personRepository;
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

    public Iterable<Person> getPersonsInHouse(long id){
        Optional<House> optionalHouse = houseRepository.findById(id);
        if(optionalHouse.isPresent()){
            House house = optionalHouse.get();
            return house.getInhabitants();
        } else {
            throw new RecordNotFoundException("This ID does not exist");
        }
    }

    public void addPersonInHouse(long id, Person person){
        Optional<House> optionalHouse = houseRepository.findById(id);
        if(optionalHouse.isPresent()){
            House house = optionalHouse.get();
            List<Person> inhabitants = house.getInhabitants();
            personRepository.save(person);
            inhabitants.add(person);
            houseRepository.save(house);
        } else {
            throw new RecordNotFoundException("This ID does not exist");
        }
    }
}
