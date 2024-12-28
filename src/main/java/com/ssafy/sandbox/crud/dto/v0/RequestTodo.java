package com.ssafy.sandbox.crud.dto.v0;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RequestTodo { // 입력받는 todo
    @NotBlank
    private String content;

    public RequestTodo() {
    }

    public RequestTodo(String content) {
        this.content = content;
    }
}