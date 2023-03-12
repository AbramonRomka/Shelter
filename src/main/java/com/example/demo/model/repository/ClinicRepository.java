package com.example.demo.model.repository;

import com.example.demo.model.entity.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Long> {
    Optional<Clinic> findByCity(String city);

    Optional<Clinic> findByPhone(String city);
}
