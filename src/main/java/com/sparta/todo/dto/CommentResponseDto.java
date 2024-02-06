package com.sparta.todo.dto;

import com.sparta.todo.Entity.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDto {

    private final String comment;

    public CommentResponseDto(Comment comment){
        this.comment = comment.getComment();
    }

}
