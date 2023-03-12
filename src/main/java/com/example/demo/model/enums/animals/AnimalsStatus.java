package com.example.demo.model.enums.animals;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AnimalsStatus {
    IN_SHELTER("в приюте"),
    SHELTERED("у клиента"),
    IN_CLINIC("на лечении"),
    MISSING("в поиске"),
    IN_DEFINED("не определён"),
    CANCELED("отмена"),
    BOOKING("бронь");

    private final String description;
}
