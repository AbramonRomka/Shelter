package com.example.demo.model.entity;

import com.example.demo.model.enums.Clinic.StatusClinic;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "vetclinic")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Clinic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "city")
    String city;

    @CreationTimestamp
    @Column(name = "created_ad", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    LocalDateTime createdAd;

    @Column(name = "updated_ad")
    LocalDateTime updatedAd;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    StatusClinic status = StatusClinic.OPEN;

    @Column(name = "phone", unique = true)
    String phone;

    @Column(name = "employee_id")
    @OneToMany(cascade = CascadeType.ALL)
    List<User> users;

    @Column(name = "animal_id")
    @OneToMany(cascade = CascadeType.ALL)
    List<Animal> animals;

    @JoinColumn(name = "shelter_id")
    @OneToOne(cascade = CascadeType.ALL)
    Shelter shelter;

}
