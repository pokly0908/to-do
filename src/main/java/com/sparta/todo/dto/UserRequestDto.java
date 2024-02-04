package com.sparta.todo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserRequestDto {

    @NotBlank
    @Size(min = 4, max = 10, message = "4자 이상 10자 이하로 입력하세요.")
    @Pattern(regexp = "^[a-z0-9]+$", message = "알파벳 소문자와 숫자만 입력 가능합니다")
    private String username;

    @Size(min = 8, max = 15, message = "8자 이상 15자 이하로 입력하세요.")
    @Pattern(regexp = "^[a-z0-9]+$", message = "알파벳 소문자와 숫자만 입력 가능합니다")
    @NotBlank
    private String password;

}
