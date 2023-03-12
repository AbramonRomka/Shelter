package com.example.demo.model.repository;

import com.example.demo.model.entity.Shelter;
import com.example.demo.model.enums.shelter.StatusShelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShelterRepository extends JpaRepository<Shelter, Long> {
    Optional<Shelter> findByCity(String city);

    Optional<Shelter> findByPhone(String phone);

    List<Shelter> findAllByStatus(StatusShelter statusShelter);


}
