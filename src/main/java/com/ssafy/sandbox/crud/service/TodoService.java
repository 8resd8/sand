package com.ssafy.sandbox.crud.service;

import com.ssafy.sandbox.crud.dto.TodosResponse;
import com.ssafy.sandbox.crud.dto.TodoRequest;
import com.ssafy.sandbox.crud.entity.Todo;
import com.ssafy.sandbox.crud.repository.TodoQueryRepository;
import com.ssafy.sandbox.crud.repository.TodoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.ssafy.sandbox.crud.util.TodoConverter.*;

@RequiredArgsConstructor
@Service
@Transactional
public class TodoService {

    private final TodoRepository todoRepository;
    private final TodoQueryRepository todoQueryRepository;

    public TodosResponse findAllTodo() {
        return todoQueryRepository.findAllTodo();
    }

    public void save(TodoRequest request) {
        todoRepository.save(toEntity(request));
    }

    public void updateToggle(Long todoId) {
        Todo findTodo = todoRepository.findById(todoId).orElseThrow(() -> new IllegalStateException("존재하지 않는 ID: " + todoId));
        findTodo.updateToggle();
    }

    public void deleteTodo(Long todoId) {
        todoRepository.deleteById(todoId);
    }


}
