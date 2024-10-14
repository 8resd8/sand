package com.ssafy.sandbox.crud.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class ResponseTodo { // API 응답용
    private final Long id;
    private final String content;
    private final boolean completed;
}