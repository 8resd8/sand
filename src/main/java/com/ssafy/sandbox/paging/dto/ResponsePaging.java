package com.ssafy.sandbox.paging.dto;

import com.ssafy.sandbox.crud.dto.ResponseTodo;

import java.util.List;

public record ResponsePaging(String message, int currentPageNumber,
                             int size, boolean hasNext,
                             List<ResponseTodo> todos) {
}