package com.example.lospollos.services;

import com.example.lospollos.dtos.CarRequestDto;
import com.example.lospollos.exceptions.RecordNotFoundException;
import com.example.lospollos.models.Car;
import com.example.lospollos.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarService {


    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository){
        this.carRepository = carRepository;
    }

    public Iterable<Car> getAllCars(){
        return carRepository.findAll();
    }

    public Car getCar(long id){
        Optional<Car> optionalCar = carRepository.findById(id);

        if(optionalCar.isPresent()){
            return optionalCar.get();
        } else {
            throw new RecordNotFoundException("this ID doesn't exist");
        }

    }

    public void deleteCar(long id){
        if(carRepository.existsById(id)){
            carRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("ID does not exist");
        }

    }

    public long addCar(CarRequestDto carRequestDto){
        Car car = new Car();
        car.setBrand(carRequestDto.getBrand());
        car.setType(carRequestDto.getType());

        Car newCar = carRepository.save(car);
        return newCar.getId();
    }
}
