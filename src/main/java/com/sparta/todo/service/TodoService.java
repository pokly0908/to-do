package com.sparta.todo.service;

import com.sparta.todo.Entity.Todo;
import com.sparta.todo.Entity.User;
import com.sparta.todo.dto.TodoListResponseDto;
import com.sparta.todo.dto.TodoRequestDto;
import com.sparta.todo.dto.TodoResponseDto;
import com.sparta.todo.repository.TodoRepository;
import com.sparta.todo.repository.UserRepository;
import com.sparta.todo.security.UserDetailsImpl;
import jakarta.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;
    public TodoResponseDto readTodo(Long todoId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(
            () -> new IllegalArgumentException("메모가 없습니다.")
        );
        return new TodoResponseDto(todo);
    }

    public TodoListResponseDto readTodo() {
        List<User> users = userRepository.findAll();
        Map<String, List<TodoResponseDto>> todoByName = new HashMap<>();

        for(User user : users){
            List<Todo> todo = todoRepository.findByUser_UserId(user.getUserId());
            if(todo.isEmpty()){
                continue;
            }
            List<TodoResponseDto> todoResponseDtos = todo.stream()
                .map(TodoResponseDto::new)
                .collect(Collectors.toList());
            todoByName.put(user.getUsername(), todoResponseDtos);
        }
        return new TodoListResponseDto(todoByName);
    }
    public TodoResponseDto createTodo(TodoRequestDto todoRequestDto, UserDetailsImpl userDetail) {
        String title = todoRequestDto.getTitle();
        String content = todoRequestDto.getContent();
        String username = userDetail.getUsername();

        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

        Todo todo = new Todo(title, content, user);

        todoRepository.save(todo);

        return new TodoResponseDto(todo, username);
    }

@Transactional
    public TodoResponseDto updateTodo(Long todoId, TodoRequestDto todoRequestDto, UserDetailsImpl userDetails) {

            User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(
                () -> new IllegalArgumentException("유저를 찾을 수 없습니다.")
            );
            Todo todo = todoRepository.findById(todoId).orElseThrow(
                () -> new IllegalArgumentException("메모를 찾을 수 없습니다.")
            );
            if (!user.getUsername().equals(todo.getUser().getUsername())) {
                throw new IllegalArgumentException("정보를 찾을 수 없습니다.");
            }

            todo.update(todoRequestDto);
            TodoResponseDto todoResponseDto = new TodoResponseDto(todo,
                user.getUsername());

            return todoResponseDto;
    }

    @Transactional
    public TodoResponseDto completeTodo(Long todoId, UserDetailsImpl userDetails) {
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(
            () -> new IllegalArgumentException("유저를 찾을 수 없습니다.")
        );
        Todo todo = todoRepository.findById(todoId).orElseThrow(
            () -> new IllegalArgumentException("메모를 찾을 수 없습니다.")
        );
        if (!user.getUsername().equals(todo.getUser().getUsername())) {
            throw new IllegalArgumentException("정보를 찾을 수 없습니다.");
        }

        todo.complete();
        TodoResponseDto todoResponseDto = new TodoResponseDto(todo,
            user.getUsername());

        return todoResponseDto;
    }
}
