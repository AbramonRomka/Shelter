package com.example.demo.model.entity;

import com.example.demo.model.enums.shelter.StatusShelter;
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
@Table(name = "shelters")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Shelter {
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
    StatusShelter status = StatusShelter.OPEN;

    @Column(name = "phone", unique = true)
    String phone;

    @Column(name = "employees_id")
    @OneToMany(cascade = CascadeType.ALL)
    List<User> users;

    @Column(name = "animals_id")
    @OneToMany(cascade = CascadeType.ALL)
    List<Animal> animals;

    @JoinColumn(name = "vetclinic_id")
    @OneToOne(cascade = CascadeType.ALL)
    Clinic clinic;


}
