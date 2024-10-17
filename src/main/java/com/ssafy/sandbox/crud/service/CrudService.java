package com.ssafy.sandbox.crud.service;

import com.ssafy.sandbox.crud.dto.RequestTodo;
import com.ssafy.sandbox.crud.dto.ResponseTodo;

import java.util.List;


public interface CrudService {

    void saveTodo(RequestTodo requestTodo);

    int updateToggle(Long id);

    ResponseTodo findById(Long id);

    List<ResponseTodo> findAll();

    int deleteTodo(Long id);

    List<ResponseTodo> cursorPaging(Long start, int count);

    List<ResponseTodo> offsetPaging(int size, int page);

    int getTotalCount();
}