package com.example.lospollos.repositories;

import com.example.lospollos.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {

}
