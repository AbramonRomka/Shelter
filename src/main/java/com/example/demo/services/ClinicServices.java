package com.example.demo.services;

import com.example.demo.model.dto.ClinicDto;
import com.example.demo.model.dto.ShelterDto;
import com.example.demo.model.entity.Animal;
import com.example.demo.model.entity.Clinic;
import com.example.demo.model.enums.Clinic.StatusClinic;

import java.util.List;

public interface ClinicServices {
    String createClinic(ClinicDto clinicDto);

    List<Clinic> findAllclinic();

    List<Animal> animalAll(String phone);

}
