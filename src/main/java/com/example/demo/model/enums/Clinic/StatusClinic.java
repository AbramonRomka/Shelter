package com.example.demo.model.enums.Clinic;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusClinic {
    OPEN("Открыт"),
    CLOSE("Закрыт"),
    REPAIR("На ремонте");

    private final String description;
}
