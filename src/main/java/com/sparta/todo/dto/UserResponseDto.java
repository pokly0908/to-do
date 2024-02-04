package com.sparta.todo.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;



@Getter
@RequiredArgsConstructor
public class UserResponseDto {

    private final String message;
    private final HttpStatus httpStatus;
}
