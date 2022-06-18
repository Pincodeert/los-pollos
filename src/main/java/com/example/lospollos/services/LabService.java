package com.example.lospollos.services;

import com.example.lospollos.dtos.LabRequestDto;
import com.example.lospollos.exceptions.RecordNotFoundException;
import com.example.lospollos.models.Lab;
import com.example.lospollos.repositories.LabRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LabService {

    private final LabRepository labRepository;

    public LabService(LabRepository labRepository){
        this.labRepository = labRepository;
    }

    public Iterable<Lab> getAllLabs(){
        return labRepository.findAll();
    }

    public Lab getLab(long id){
        Optional<Lab> optinalLab = labRepository.findById(id);

        if(optinalLab.isPresent()){
            return optinalLab.get();
        } else {
            throw new RecordNotFoundException("This ID doesn't exist");
        }
    }

    public void deleteLab(long id){
        if(labRepository.existsById(id)){
            labRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("this id does not exist");
        }
    }

    public long addLab(LabRequestDto labRequestDto){
        Lab newLab = new Lab();
        newLab.setName(labRequestDto.getName());
        newLab.setType(labRequestDto.getType());
        newLab.setLocation(labRequestDto.getLocation());
        Lab lab = labRepository.save(newLab);
        long newId = lab.getId();
        return newId;
    }

    public void updateLab(long id, Lab lab){
        Lab existingLab = labRepository.findById(id).orElse(null);
        if(!existingLab.getName().isEmpty()){
            existingLab.setName(lab.getName());
        }
        if(!existingLab.getType().isEmpty()) {
            existingLab.setType(lab.getType());
        }
        if(!lab.getLocation().isEmpty()){
            existingLab.setLocation(lab.getLocation());
        }
        labRepository.save(existingLab);

    }



}
