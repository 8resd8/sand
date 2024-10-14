package com.ssafy.sandbox.crud.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RequestTodo { // 입력받는 todo
    private final String content;

    public RequestTodo(String content) {
        this.content = content;
    }
}