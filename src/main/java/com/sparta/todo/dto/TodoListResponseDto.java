package com.sparta.todo.dto;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class TodoListResponseDto {

    private Map<String, List<TodoResponseDto>> scheduleByName;

    public TodoListResponseDto(Map<String, List<TodoResponseDto>> scheduleByName) {
        this.scheduleByName = scheduleByName.entrySet().stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> entry.getValue().stream()
                    .map(scheduleResponseDto -> new TodoResponseDto(scheduleResponseDto))
                    .collect(Collectors.toList())
            ));
    }
}
