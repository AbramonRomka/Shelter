package com.example.demo.services.impl;

import com.example.demo.exception.CustomException;
import com.example.demo.model.dto.AnimalDto;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.entity.*;
import com.example.demo.model.enums.animals.AnimalsStatus;
import com.example.demo.model.repository.*;
import com.example.demo.services.AnimalServices;

import com.example.demo.services.UserServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AnimalServicesImpl implements AnimalServices {

    private final AnimalRepository animalRepository;
    private final ShelterRepository shelterRepository;
    private final ObjectMapper mapper;
    private final UserRepository userRepository;
    private final ClinicRepository clinicRepository;

    private final UserServices userServices;


    @Override
    public void statementCreateUser(Principal principal, AnimalDto animalDto) { //добавление заявления от клиента
        User user = userRepository.findByPhone(principal.getName()).get();
        statementCreate(principal, animalDto, user);
    }


    public void statementCreateEmployee(Principal principal, AnimalDto animalDto, UserDto userDto) { //добавление заявления от сотрудника

        User user;

        if (userRepository.findByPhone(userDto.getPhone()).isEmpty()) {
            userServices.createUser(userDto);
        }
        user = userRepository.findByPhone(userDto.getPhone()).get();
        statementCreate(principal, animalDto, user);
    }

    private void statementCreate(Principal principal, AnimalDto animalDto, User user) {
        animalDto.setAnimalsStatus(AnimalsStatus.MISSING);
        Long id = createAnimal(animalDto, principal.getName());

        Animal animal = animalRepository.findById(id).get();
        animal.setUser(user);

        List<Animal> animals = user.getAnimals();
        animals.add(animal);
        user.setAnimals(animals);

        animalRepository.save(animal);
    }

    @Override
    public Long createAnimal(AnimalDto animalDto, String phone) {

        Shelter shelter = shelterRepository.findByCity(userRepository.findByPhone(phone).get().getCity()).get();

        Animal animal = mapper.convertValue(animalDto, Animal.class);

        if (animalDto.getAnimalsStatus() == AnimalsStatus.IN_CLINIC) {
            Clinic clinic = clinicRepository.findByCity(userRepository.findByPhone(phone).get().getCity()).orElseThrow(() ->
                    new CustomException("Клиники в городе не существует!", HttpStatus.NOT_FOUND));
            List<Animal> animals = clinic.getAnimals();
            animals.add(animal);
            clinic.setAnimals(animals);
            animal.setAnimalsStatus(AnimalsStatus.IN_CLINIC);
            animal.setClinic(clinic);
        } else {
            List<Animal> animals = shelter.getAnimals();
            animals.add(animal);
            shelter.setAnimals(animals);
            animal.setShelter(shelter);
        }

        animalRepository.save(animal);
        return animal.getId();
    }

    @Override
    public String transferAnimal(Animal animal, User user) {

        if (animal.getUser() != null) {
            User user1 = animal.getUser();
            List<Animal> animals = user1.getAnimals();
            animals.remove(animal);
            user1.setAnimals(animals);

            animal.setUser(null);
        }

        List<Animal> animals = user.getAnimals();
        animals.add(animal);
        user.setAnimals(animals);

        animal.setUser(user);
        animal.setAnimalsStatus(AnimalsStatus.SHELTERED);


        animalRepository.save(animal);


        return "Животное под номером " + animal.getId() + " передано клиенту с номером: " + user.getPhone();
    }

    @Override
    public void editAnimal(Animal animal, AnimalsStatus animalsStatus, String description) {
        animal.setAnimalsStatus(animalsStatus);
        if (!description.matches("^\\s*$")) {
            animal.setDescription(description);
        }
        animal.setUserId(null);
        animalRepository.save(animal);
    }

    @Override
    public List<Animal> findAllAnimals() {
        return animalRepository.findAll();
    }

    @Override
    public void booking(Animal animal, Principal principal) {

        animal.setAnimalsStatus(AnimalsStatus.BOOKING);
        animal.setUserId(userRepository.findByPhone(principal.getName()).get().getId());
        animalRepository.save(animal);
    }

    @Override
    public List<Animal> findBooking(Principal principal) {
        Long id = userRepository.findByPhone(principal.getName()).get().getId();
        return animalRepository.findAllByUserId(id);
    }

    @Override
    public Animal getAnimal(Long id) {
        return animalRepository.findById(id).get();
    }
}

















