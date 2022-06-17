package com.example.lospollos.repositories;

import com.example.lospollos.models.House;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepository extends JpaRepository<House, Long> {

}
