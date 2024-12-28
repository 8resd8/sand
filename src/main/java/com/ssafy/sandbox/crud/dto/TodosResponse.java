package com.ssafy.sandbox.crud.dto;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class TodosResponse {

    private List<TodoResponse> todos;

    public TodosResponse(List<TodoResponse> todos) {
        this.todos = todos;
    }
}
