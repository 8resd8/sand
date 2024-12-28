package com.ssafy.sandbox.crud.controller;

import com.ssafy.sandbox.crud.dto.TodoRequest;
import com.ssafy.sandbox.crud.dto.TodoResponse;
import com.ssafy.sandbox.crud.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping({"/", "/todos"})
@RequiredArgsConstructor
public class CrudController {

    private final TodoService todoService;


    @GetMapping
    public ResponseEntity<List<TodoResponse>> getAll() {
        return ResponseEntity.ok(todoService.findAllTodo());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveTodo(@Validated @RequestBody TodoRequest request) {
        todoService.save(request);
    }

    @PatchMapping("/{todoId}")
    @ResponseStatus(HttpStatus.OK)
    public void UpdateTodo(@PathVariable Long todoId) {
        todoService.updateToggle(todoId);
    }

    @DeleteMapping("/{todoId}")
    @ResponseStatus(HttpStatus.OK)
    public void DeleteTodo(@PathVariable Long todoId) {
        todoService.deleteTodo(todoId);
    }
}