package com.ssafy.sandbox.crud.dao;

import com.ssafy.sandbox.crud.dto.ResponseTodo;

import java.util.List;

public interface TodoDao {

    ResponseTodo createTodo();

    int insertTodo(ResponseTodo responseTodo);

    ResponseTodo selectTodo(Long id);

    List<ResponseTodo> selectAll();

    int deleteById(Long id);

    int updateToggle(Long id, boolean completed);
}
