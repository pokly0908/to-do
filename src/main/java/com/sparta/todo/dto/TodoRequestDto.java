package com.sparta.todo.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class TodoRequestDto {

    @NotBlank
    private String title;
    @NotBlank
    private String content;
}
