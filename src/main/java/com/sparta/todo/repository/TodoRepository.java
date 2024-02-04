package com.sparta.todo.repository;

import com.sparta.todo.Entity.Todo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findByUser_UserId(Long userId);
}
