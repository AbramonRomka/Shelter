package com.example.demo.model.dto;

import com.example.demo.model.enums.user.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    String name;
    String surname;
    String city;
    String email;
    String phone;
    Role role;
    Boolean patrol;

//    String dateOfBirth;
//    Gender gender;

    boolean active;
    String password;

//    List <AnimalDto> animals; //создали список животных у клиента //Это нужно только если мы хотим при создании клиента, сразу добавлять животных
// создали список животных у клиента. Гоняем не сущности, а трансферные классы
}