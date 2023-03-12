package com.example.demo.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public class CustomException extends RuntimeException {

    private final String message;
    private final HttpStatus httpStatus;
}
