package com.example.lospollos.repositories;

import com.example.lospollos.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Iterable<Person> findAllByLastName(String lastName);
    Iterable<Person> findAllByLastNameContainingIgnoreCase(String lastName);
    Iterable<Person> findAllByFirstName(String firstName);


    //Als de voorgeprogrammeerde queries zoals hierboven niet jouw gewenste zoekopdracht dekken:
    // moet je zelf een query programmeren:
    @Query(value = "SELECT * FROM persons p WHERE p.lastname LIKE %:s%", nativeQuery = true) //using SQL
    Iterable<Person> searchByLastnameLike(@Param("s") String s);

    //alternatief, using JPQL:
    //@Query(value = SELECT p FROM person p WHERE s.lastname LIKE%:s%")
}
