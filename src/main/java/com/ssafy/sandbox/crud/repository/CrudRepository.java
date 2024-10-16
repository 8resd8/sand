package com.ssafy.sandbox.crud.repository;

import com.ssafy.sandbox.crud.dto.RequestTodo;
import com.ssafy.sandbox.crud.dto.ResponseTodo;

import java.util.List;

public interface CrudRepository {

    void saveTodo(RequestTodo requestTodo);

    int updateToggle(Long id);

    ResponseTodo findById(Long id);

    List<ResponseTodo> findAll();

    int deleteTodo(Long id);

    List<ResponseTodo> findSubset(Long cursorId, int count);
}
