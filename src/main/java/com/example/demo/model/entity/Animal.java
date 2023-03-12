package com.example.demo.model.entity;

import com.example.demo.model.enums.animals.ColorAnimal;
import com.example.demo.model.enums.animals.Species;
import com.example.demo.model.enums.animals.AnimalsStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "animals")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "species")
    @Enumerated(EnumType.STRING)
    Species species;

    @Column(name = "color_animal")
    ColorAnimal colorAnimal;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    AnimalsStatus animalsStatus = AnimalsStatus.IN_SHELTER;

    @CreationTimestamp
    @Column(name = "created_ad", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    LocalDateTime createdAd;

    @Column(name = "updated_ad")
    LocalDateTime updatedAd;

    @Column(name = "decription")
    String description = "отсутствует";

    @Column(name = "booking_id")
    Long userId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shelter_id")
    Shelter shelter;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vetclinic_id")
    Clinic clinic;
}

