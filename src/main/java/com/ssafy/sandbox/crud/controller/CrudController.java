package com.ssafy.sandbox.crud.controller;

import com.ssafy.sandbox.crud.response.TodosResponse;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CrudController {

    private final TodosResponse todosResponse;


    @GetMapping("/todos")
    public TodosResponse readTodo() {


        return todosResponse;
    }

    @PostMapping("/todos")
    public TodosResponse createTodo() {

        return todosResponse;
    }

    @PatchMapping("/todos")
    public TodosResponse UpdateTodo() {

        return todosResponse;
    }

    @DeleteMapping("/todos")
    public TodosResponse DeleteTodo() {

        return todosResponse;
    }
}
