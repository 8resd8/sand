package com.ssafy.sandbox.paging.service;

import com.ssafy.sandbox.crud.dto.ResponseTodo;

import java.util.List;

public interface PagingService {
    int startPage(int size, int page);

    int endPage(int size, int startPage, int todoSize);

    List<ResponseTodo> pagedTotos(int size, int page);

    boolean hasNext(int size, int page);
}
