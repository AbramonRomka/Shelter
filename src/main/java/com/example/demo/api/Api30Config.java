package com.example.demo.api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

//класс для того, чтобы в шапке http://localhost:8080/swagger-ui отражалось название, которое мы хотим

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "Проект Петрова", version = "v1"))
public class Api30Config {
}
