package com.ssafy.sandbox.crud.dto;


import java.util.List;


public class ResponseTodo {
    private final String message;
    private final List<Todo> todos;

    public ResponseTodo(String message, List<Todo> todos) {
        this.message = message;
        this.todos = todos;
    }
}