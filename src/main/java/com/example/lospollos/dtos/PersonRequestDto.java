package com.example.lospollos.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PersonRequestDto {

    @NotBlank
    @Size(min = 2, max = 20)
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 20)
    private String lastName;

    @NotBlank
    @Size(min = 2, max = 20)
    private String profession;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    //extra validation per attribuut die je desgewenst kan aanroepen in de servicelaag. bv in geval van een PatchMapping,
    //waarvoor je de Annotatie @NotBlank niet kan gebruiken omdat je sowieso lege velden aanbiedt, en dus geen @Valid
    // in de PatchMapping kan toevoegen, maar waarbij je wel de van de ander valdiatie annotaties in de DTO gebruik
    // wil maken, bv de @Size. Als de code heel uitgebreid wordt, zou je er ook een aparte (Dto)klasse van kunnen maken.
    public boolean isValidFirstName(String firstName){

        if(firstName.length() < 2 || firstName.length() > 20){
            return false;
        } else {
            return true;
        }
    }
    public boolean isValidLastName(String lastName){
        if(lastName.length() < 2 || lastName.length() > 20){
            return false;
        } else {
            return true;
        }
    }
    public boolean isValidProfession(String profession){
        if(profession.length() < 2 || profession.length() > 20){
            return false;
        } else {
            return true;
        }
    }

}
