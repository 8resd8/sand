package com.ssafy.sandbox.paging.dto;

import com.ssafy.sandbox.crud.dto.ResponseTodo;
import com.ssafy.sandbox.crud.dto.Todo;

import java.util.List;

public record ResponseOffset(String message, int currentPageNumber,
                             int size, int totalPage,
                             boolean hasNext, boolean hasPrevious,
                             List<Todo> todos) {
}
