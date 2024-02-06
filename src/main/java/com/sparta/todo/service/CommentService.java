package com.sparta.todo.service;

import com.sparta.todo.Entity.Comment;
import com.sparta.todo.Entity.Todo;
import com.sparta.todo.dto.CommentRequestDto;
import com.sparta.todo.dto.CommentResponseDto;
import com.sparta.todo.repository.CommentRepository;
import com.sparta.todo.repository.TodoRepository;
import com.sparta.todo.security.UserDetailsImpl;
import jakarta.transaction.Transactional;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final TodoRepository todoRepository;
    public CommentResponseDto createComment(Long todoId, CommentRequestDto commentRequestDto, UserDetailsImpl userDetail) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new IllegalArgumentException("메모를 찾을 수 없습니다."));
        Comment comment = new Comment(commentRequestDto, todo, userDetail.getUser());
        commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }

    @Transactional
    public CommentResponseDto updateComment(Long commentId, CommentRequestDto commentRequestDto, UserDetailsImpl userDetail) {
    Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다."));
    if(!(comment.getUser().getUserId() == userDetail.getUser().getUserId())){
        throw new IllegalArgumentException("본인 댓글이 아닙니다.");
    }
    comment.update(commentRequestDto);

    return new CommentResponseDto(comment);
    }

    public void deleteComment(Long commentId, UserDetailsImpl userDetail) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다."));
        if(!(comment.getUser().getUserId() == userDetail.getUser().getUserId())){
            throw new IllegalArgumentException("본인 댓글이 아닙니다.");
        }
        commentRepository.delete(comment);
    }
}
