package com.sparta.todo.dto;

import com.sparta.todo.Entity.Todo;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter

public class TodoResponseDto {

    private String title;
    private String content;
    private String username;
    private LocalDateTime createAt;
    private boolean isCompleted;

    public TodoResponseDto(Todo todo, String username) {
        this.title = todo.getTitle();
        this.content = todo.getContent();
        this.username = username;
        this.createAt = todo.getCreateAt();
        this.isCompleted = todo.isCompleted();
    }

    public TodoResponseDto(Todo todo) {
        this.title = todo.getTitle();
        this.content = todo.getContent();
        this.username = todo.getUser().getUsername();
        this.createAt = todo.getCreateAt();
        this.isCompleted = todo.isCompleted();
    }

    public TodoResponseDto(TodoResponseDto todoResponseDto) {
        this.title = todoResponseDto.getTitle();
        this.content = todoResponseDto.getContent();
        this.username = todoResponseDto.getUsername();
        this.createAt = todoResponseDto.getCreateAt();
        this.isCompleted = todoResponseDto.isCompleted();
    }
}
