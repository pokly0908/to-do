package com.sparta.todo.controller;


import com.sparta.todo.dto.CommentDeleteDto;
import com.sparta.todo.dto.CommentRequestDto;
import com.sparta.todo.dto.CommentResponseDto;
import com.sparta.todo.security.UserDetailsImpl;
import com.sparta.todo.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todo/{todoId}/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("")
    public CommentResponseDto createComment(@PathVariable Long todoId, @Valid @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal
        UserDetailsImpl userDetail){
        CommentResponseDto commentResponseDto = commentService.createComment(todoId, commentRequestDto, userDetail);
        return commentResponseDto;
    }

    @PatchMapping("/{commentId}")
    public CommentResponseDto updateComment(@PathVariable Long commentId, @Valid @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetail){
        CommentResponseDto commentResponseDto = commentService.updateComment(commentId, commentRequestDto, userDetail);
        return commentResponseDto;
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<CommentDeleteDto> deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetail){
        commentService.deleteComment(commentId, userDetail);
        String successMessage = "삭제가 완료되었습니다.";
        return ResponseEntity.ok().body(new CommentDeleteDto(successMessage, HttpStatus.OK));
    }

}
