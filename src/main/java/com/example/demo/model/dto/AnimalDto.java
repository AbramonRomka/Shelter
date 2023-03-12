package com.example.demo.model.dto;

import com.example.demo.model.enums.animals.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AnimalDto {
    Species species;
    ColorAnimal colorAnimal;
    String description;
    AnimalsStatus animalsStatus;

}
