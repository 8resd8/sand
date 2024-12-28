package com.ssafy.sandbox.crud.service.v0;

import com.ssafy.sandbox.crud.dto.v0.Todo;
import com.ssafy.sandbox.crud.dto.v0.RequestTodo;

import java.util.List;


public interface CrudService {

    void saveTodo(RequestTodo requestTodo);

    int updateToggle(Long id);

    Todo findById(Long id);

    List<Todo> findAll();

    int deleteTodo(Long id);

    List<Todo> cursorPaging(Long start, int count);

    List<Todo> offsetPaging(int size, int page);

    int getTotalCount();
}