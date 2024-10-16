package com.ssafy.sandbox.paging.service;

import com.ssafy.sandbox.crud.dto.ResponseTodo;

import java.util.List;

public interface PagingService {


    List<ResponseTodo> pagedTotos(int size, Long cursorId);

    boolean hasNext(Long cursorId, int size);

    Long getNextCursor(List<ResponseTodo> todos);
}
