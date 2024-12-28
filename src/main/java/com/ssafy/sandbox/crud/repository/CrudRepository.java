package com.ssafy.sandbox.crud.repository;

import com.ssafy.sandbox.crud.dto.v0.Todo;
import com.ssafy.sandbox.crud.dto.v0.RequestTodo;

import java.util.List;

public interface CrudRepository {

    void saveTodo(RequestTodo requestTodo);

    int updateToggle(Long id);

    Todo findById(Long id);

    List<Todo> findAll();

    int deleteTodo(Long id);

    List<Todo> cursorPaging(Long cursorId, int count);

    List<Todo> offsetPaging(int size, int offset);

    int getTotalCount();
}
