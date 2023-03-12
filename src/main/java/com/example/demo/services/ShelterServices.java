package com.example.demo.services;

import com.example.demo.model.dto.ShelterDto;
import com.example.demo.model.entity.Animal;
import com.example.demo.model.entity.Shelter;
import com.example.demo.model.enums.animals.AnimalsStatus;
import com.example.demo.model.enums.shelter.StatusShelter;

import java.util.List;


public interface ShelterServices {
    void createShelter(ShelterDto shelterDto);

    Shelter getShelter(String city);

    List<Animal> animalAll(String phone);

    List<Shelter> findAllshelters();

    List<Shelter> findAllStatusShelters(StatusShelter statusShelter);

    List<Animal> animalAllStatus(Shelter shelter, AnimalsStatus inShelter);

}
