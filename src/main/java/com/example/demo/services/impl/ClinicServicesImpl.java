package com.example.demo.services.impl;

import com.example.demo.exception.CustomException;
import com.example.demo.model.dto.ClinicDto;
import com.example.demo.model.entity.Animal;
import com.example.demo.model.entity.Clinic;
import com.example.demo.model.entity.Shelter;
import com.example.demo.model.repository.ClinicRepository;
import com.example.demo.model.repository.ShelterRepository;
import com.example.demo.model.repository.UserRepository;
import com.example.demo.services.ClinicServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ClinicServicesImpl implements ClinicServices {
    private final ObjectMapper mapper;
    private final ShelterRepository shelterRepository;
    private final ClinicRepository clinicRepository;
    private final UserRepository userRepository;

    @Override
    public String createClinic(ClinicDto clinicDto) {

        if (clinicDto.getPhone() == null || clinicDto.getCity() == null) {
            throw new CustomException("Заполните все поля!", HttpStatus.CONFLICT);
        }

        Clinic clinic = mapper.convertValue(clinicDto, Clinic.class);
        Shelter shelter = shelterRepository.findByCity(clinicDto.getCity()).orElseThrow(
                () -> new CustomException("Приют в данном городе не найден!", HttpStatus.NOT_FOUND)
        );
        clinicRepository.findByCity(clinicDto.getCity()).ifPresent(h -> {
            throw new CustomException("Клиника в таком городе уже существует", HttpStatus.CONFLICT);
        });
        clinicRepository.findByPhone(clinicDto.getPhone()).ifPresent(
                s -> {
                    throw new CustomException("Приют c номером " + clinicDto.getPhone() + " уже существует!", HttpStatus.CONFLICT);
                });


        clinic.setShelter(shelter);
        clinicRepository.save(clinic);
        shelter.setClinic(clinic);
        shelterRepository.save(shelter);

        return "Клиника в городе " + shelter.getCity() + " успешно создана.";
    }

    @Override
    public List<Clinic> findAllclinic() {
        return clinicRepository.findAll();
    }

    @Override
    public List<Animal> animalAll(String phone) {

        Clinic clinic = clinicRepository.findByCity(userRepository.findByPhone(phone).get().getCity()).orElse(new Clinic());

        return clinic.getAnimals();
    }

    public Clinic findClinic(String phone) {
        return clinicRepository.findByPhone(phone)
                .orElseThrow(() ->
                        new CustomException("Клиники с номером " + phone + " не существует!", HttpStatus.CONFLICT));
    }
}

















