package com.ssafy.sandbox.crud.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ResponseTodo { // API 응답용
    private Long id;
    private String content;
    private boolean completed;

    public ResponseTodo() {
    }

    public ResponseTodo(Long id, String content, boolean completed) {
        this.id = id;
        this.content = content;
        this.completed = completed;
    }
}