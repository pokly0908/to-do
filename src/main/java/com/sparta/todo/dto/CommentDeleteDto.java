package com.sparta.todo.dto;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public class CommentDeleteDto {
    private final String message;
    private final HttpStatus httpStatus;

}
