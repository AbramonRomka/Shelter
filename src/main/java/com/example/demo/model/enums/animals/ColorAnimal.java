package com.example.demo.model.enums.animals;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ColorAnimal {
    RED("красный"),
    MULTICOLOUR("многоцветный"),
    GRAY("серый"),
    BLACK("черный"),
    WHITE("белый");

    private final String description;
}
