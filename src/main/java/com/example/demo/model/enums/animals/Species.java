package com.example.demo.model.enums.animals;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Species {
    PARROT("Птица"),
    DOG("Собака"),
    CAT("Кошка"),
    RAT("Мышь"),
    LIZARD("Ящерица"),
    RACCOON("Енот");

    private final String description;
}
