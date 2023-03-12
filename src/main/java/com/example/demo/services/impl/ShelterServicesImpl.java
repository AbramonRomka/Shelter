package com.example.demo.services.impl;

import com.example.demo.exception.CustomException;
import com.example.demo.model.dto.ShelterDto;
import com.example.demo.model.entity.Animal;
import com.example.demo.model.entity.Shelter;
import com.example.demo.model.enums.animals.AnimalsStatus;
import com.example.demo.model.enums.shelter.StatusShelter;
import com.example.demo.model.repository.ShelterRepository;
import com.example.demo.model.repository.UserRepository;
import com.example.demo.services.ShelterServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ShelterServicesImpl implements ShelterServices {

    private final ShelterRepository shelterRepository;
    private final ObjectMapper mapper;
    private final UserRepository userRepository;

    @Override
    public void createShelter(ShelterDto shelterDto) {

        shelterRepository.findByCity(shelterDto.getCity()).ifPresent(
                s -> {
                    throw new CustomException("Приют в городе " + shelterDto.getCity() + " уже существует!", HttpStatus.CONFLICT);
                });
        shelterRepository.findByPhone(shelterDto.getPhone()).ifPresent(
                s -> {
                    throw new CustomException("Приют c номером " + shelterDto.getPhone() + " уже существует!", HttpStatus.CONFLICT);
                });
        if (shelterDto.getPhone() == null || shelterDto.getCity() == null) {
            throw new CustomException("Заполните все поля!", HttpStatus.CONFLICT);
        }

        Shelter shelter = mapper.convertValue(shelterDto, Shelter.class);
        shelter.setCreatedAd(LocalDateTime.now());
        shelterRepository.save(shelter);

    }

    @Override
    public Shelter getShelter(String city) {

        return shelterRepository.findByCity(city).get();
    }

    @Override
    public List<Animal> animalAll(String phone) {
        Shelter shelter = shelterRepository.findByCity(userRepository.findByPhone(phone).get().getCity()).get();
        return shelter.getAnimals();
    }

    @Override
    public List<Shelter> findAllshelters() {
        return shelterRepository.findAll();
    }

    @Override
    public List<Shelter> findAllStatusShelters(StatusShelter statusShelter) {
        return shelterRepository.findAllByStatus(statusShelter);
    }

    @Override
    public List<Animal> animalAllStatus(Shelter shelter, AnimalsStatus inShelter) {
        List<Animal> animals = shelter.getAnimals();
        return animals;
    }

    public Shelter findShelter(String phone) {
        return shelterRepository.findByPhone(phone)
                .orElseThrow(() ->
                        new CustomException("Магазина с номером " + phone + " не существует!", HttpStatus.CONFLICT));
    }
}
