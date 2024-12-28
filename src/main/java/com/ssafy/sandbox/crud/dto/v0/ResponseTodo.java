package com.ssafy.sandbox.crud.dto.v0;


import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
public class ResponseTodo {
    private String message;
    private List<Todo> todos;

    public ResponseTodo() {
    }

    public ResponseTodo(String message, List<Todo> todos) {
        this.message = message;
        this.todos = todos;
    }
}