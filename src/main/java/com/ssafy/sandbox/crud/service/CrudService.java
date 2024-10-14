package com.ssafy.sandbox.crud.service;

import com.ssafy.sandbox.crud.response.TodosResponse;

public interface CrudService {

    TodosResponse readTodo();

    TodosResponse createTodo();

    TodosResponse UpdateTodo();

    TodosResponse DeleteTodo();
}