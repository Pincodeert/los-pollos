package com.example.lospollos.services;

import com.example.lospollos.dtos.PersonRequestDto;
import com.example.lospollos.exceptions.BadRequestException;
import com.example.lospollos.exceptions.RecordNotFoundException;
import com.example.lospollos.models.Person;
import com.example.lospollos.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Iterable<Person> getAllPersons(String lastName){

        if(lastName.isEmpty()){
            return personRepository.findAll();
        } else {
            return personRepository.findAllByLastNameContainingIgnoreCase(lastName);
        }


    }

    public Person getPerson(long id){

        Optional<Person> optionalPerson = personRepository.findById(id);

        if(optionalPerson.isPresent()){
            return optionalPerson.get();
        } else {
            throw new RecordNotFoundException("ID does not exist"); // voorlopige oplossing. Dit genereert een 500 http statuscode
        }

    }

    public void deletePerson(long id){
        if(personRepository.existsById(id)){
            personRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("ID does not exist");
        }
    }



    public long addPerson(PersonRequestDto personRequestDto){
        String firstName = personRequestDto.getFirstName();
        List<Person> persons = (List<Person>) personRepository.findAllByFirstName(firstName);
        if((persons.size() > 0)){
            throw new BadRequestException("firstname already exists!");
        }

        Person newPerson = new Person();
        newPerson.setFirstName(personRequestDto.getFirstName());
        newPerson.setLastName(personRequestDto.getLastName());
        newPerson.setProfession(personRequestDto.getProfession());

        Person person = personRepository.save(newPerson);
        return person.getId();

    }

    public void updatePerson(long id, PersonRequestDto personRequestDto){

        Person existingPerson = personRepository.findById(id).orElse(null);

        if(!personRequestDto.getFirstName().isEmpty()){
            existingPerson.setFirstName(personRequestDto.getFirstName());
        }
        if(!personRequestDto.getLastName().isEmpty()){
            existingPerson.setLastName(personRequestDto.getLastName());
        }
        if(!personRequestDto.getProfession().isEmpty()){
            existingPerson.setProfession(personRequestDto.getProfession());
        }
        personRepository.save(existingPerson);
    }

    public void partialUpdatePerson(long id, PersonRequestDto personRequestDto){

        Person existingPerson = personRepository.findById(id).orElse(null); // or else voorlopige oplossing. niet zo als het moet

        boolean validFirstName =  personRequestDto.isValidFirstName(personRequestDto.getFirstName());

        if(validFirstName) {

            if (!(personRequestDto.getFirstName() == null) && !personRequestDto.getFirstName().isEmpty()) {
                existingPerson.setFirstName(personRequestDto.getFirstName());
            }
            if (!(personRequestDto.getLastName() == null) && !personRequestDto.getLastName().isEmpty()) {
                existingPerson.setLastName(personRequestDto.getLastName());
            }
            if (!(personRequestDto.getProfession() == null) && !personRequestDto.getProfession().isEmpty()) {
                existingPerson.setProfession(personRequestDto.getProfession());
            }
            personRepository.save(existingPerson);
        } else {
            throw new BadRequestException("firstname is too long or too short");
        }
    }
}

