package com.ssafy.sandbox.paging.dto;

import com.ssafy.sandbox.crud.dto.Todo;

import java.util.List;

public record ResponseCursor(String message, Long lastId,
                             int size, boolean hasNext,
                             List<Todo> todos) {
}