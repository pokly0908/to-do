package com.sparta.todo.controller;


import com.sparta.todo.dto.TodoListResponseDto;
import com.sparta.todo.dto.TodoRequestDto;
import com.sparta.todo.dto.TodoResponseDto;
import com.sparta.todo.security.UserDetailsImpl;
import com.sparta.todo.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    //메모 생성
    @PostMapping("/create")
    public TodoResponseDto createTodo(@Valid @RequestBody TodoRequestDto todoRequestDto, @AuthenticationPrincipal
        UserDetailsImpl userDetail){
        TodoResponseDto todoResponseDto = todoService.createTodo(todoRequestDto, userDetail);
        return todoResponseDto;
    }

    @GetMapping("/{todoId}")
    public TodoResponseDto readTodo(@PathVariable Long todoId) {
        TodoResponseDto todoResponseDto = todoService.readTodo(todoId);
        return todoResponseDto;
    }

    @GetMapping("")
    public TodoListResponseDto readTodo() {
        TodoListResponseDto todoListResponseDto = todoService.readTodo();
        return todoListResponseDto;
    }

    @PatchMapping("/{todoId}")
    public TodoResponseDto updateSchedule(@PathVariable Long todoId,
        @Valid @RequestBody TodoRequestDto todoRequestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        TodoResponseDto scheduleResponseDto = todoService.updateTodo(todoId,
            todoRequestDto, userDetails);
        return scheduleResponseDto;
    }

    @PatchMapping("/{todoId}/complete")
    public TodoResponseDto completeSchedule(@PathVariable Long todoId,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        TodoResponseDto scheduleResponseDto = todoService.completeTodo(todoId,
            userDetails);
        return scheduleResponseDto;
    }

}
