package com.example.demo.services;

import com.example.demo.model.dto.AnimalDto;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.entity.Animal;
import com.example.demo.model.entity.User;
import com.example.demo.model.enums.animals.AnimalsStatus;

import java.security.Principal;
import java.util.List;

public interface AnimalServices {

    void statementCreateEmployee(Principal principal, AnimalDto animalDto, UserDto userDto);

    void statementCreateUser(Principal principal, AnimalDto animalDto);

    Long createAnimal(AnimalDto animalDto, String phone);


    String transferAnimal(Animal animal, User user);


    void editAnimal(Animal animal, AnimalsStatus animalsStatus, String description);

    List<Animal> findAllAnimals();

    void booking(Animal animal, Principal principal);

    List<Animal> findBooking(Principal principal);

    Animal getAnimal(Long id);

}
