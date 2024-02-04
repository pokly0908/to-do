package com.sparta.todo.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.todo.dto.TodoRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "TODO")
@NoArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todoId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(updatable = false)
    private LocalDateTime createAt;

    @Column(nullable = false)
    private boolean isCompleted;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    public Todo(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.createAt = LocalDateTime.now();
        this.isCompleted = false;
    }

    public void update(TodoRequestDto todoRequestDto) {
        this.title = todoRequestDto.getTitle();
        this.content = todoRequestDto.getContent();
    }
    public void complete() {
        this.isCompleted = true;
    }
}
