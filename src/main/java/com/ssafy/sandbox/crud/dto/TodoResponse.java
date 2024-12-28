package com.ssafy.sandbox.crud.dto;

import lombok.Getter;

@Getter
public class TodoResponse {

    private Long id;
    private String content;
    private boolean completed;

    public TodoResponse(Long id, String content, boolean completed) {
        this.id = id;
        this.content = content;
        this.completed = completed;
    }
}
