package com.sparta.todo.repository;

import com.sparta.todo.Entity.Comment;
import com.sparta.todo.Entity.Todo;
import com.sparta.todo.dto.CommentResponseDto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
