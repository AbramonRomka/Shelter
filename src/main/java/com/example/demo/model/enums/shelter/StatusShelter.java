package com.example.demo.model.enums.shelter;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusShelter {
    OPEN("Открыт"),
    CLOSE("Закрыт"),
    REPAIR("На ремонте");

    private final String description;
}
