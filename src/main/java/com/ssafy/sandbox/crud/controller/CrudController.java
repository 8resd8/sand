package com.ssafy.sandbox.crud.controller;

import com.ssafy.sandbox.crud.dto.TodoRequest;
import com.ssafy.sandbox.crud.dto.TodosResponse;
import com.ssafy.sandbox.crud.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/", "/todos"})
@RequiredArgsConstructor
public class CrudController {

    private final TodoService todoService;


    @GetMapping
    public TodosResponse getAll() {
        return todoService.findAllTodo();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveTodo(@Validated @RequestBody TodoRequest request) {
        todoService.save(request);
    }

    @PatchMapping("/{todoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void UpdateTodo(@PathVariable Long todoId) {
        todoService.updateToggle(todoId);
    }

    @DeleteMapping("/{todoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void DeleteTodo(@PathVariable Long todoId) {
        todoService.deleteTodo(todoId);
    }
}