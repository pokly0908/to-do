package com.sparta.todo.dto;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class TodoListResponseDto {

    private Map<String, List<TodoResponseDto>> TodoByName;

    public TodoListResponseDto(Map<String, List<TodoResponseDto>> TodoByName) {
        this.TodoByName = TodoByName.entrySet().stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> entry.getValue().stream()
                    .map(todoResponseDto -> new TodoResponseDto(todoResponseDto))
                    .collect(Collectors.toList())
            ));
    }
}
