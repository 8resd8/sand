package com.ssafy.sandbox.crud.controller;

import com.ssafy.sandbox.crud.dto.ResponseTodo;
import com.ssafy.sandbox.crud.dto.RequestTodo;
import com.ssafy.sandbox.crud.repository.CrudRepository;
import com.ssafy.sandbox.crud.repository.MemoryCrudRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping({"/", "/todos"})
public class CrudController {
;

    private final CrudRepository crudRepository;

    @GetMapping
    public ResponseEntity<Map<String, Object>> readTodo() {
        log.info("readTodo: {}", crudRepository);

        List<ResponseTodo> todos = crudRepository.findAll();
        log.info("todos: {}", todos);
        HashMap<String, Object> response = new HashMap<>();
        response.put("message", "정상적으로 요청되었습니다.");
        response.put("todos", todos);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createTodo(@Validated @RequestBody RequestTodo todos, BindingResult bindingResult) {
        log.info("createTodo: {}", todos);

        Map<String, String> response = new HashMap<>();
        response.put("message", todos.getContent());
        crudRepository.saveTodo(todos);

        return ResponseEntity.of(Optional.of(response));
    }

    @PatchMapping("/{todoId}")
    public ResponseEntity<Map<String, String>> UpdateTodo(@PathVariable Long todoId) {
        log.info("UpdateTodo: {}", todoId);
        crudRepository.updateToggle(todoId);
        Map<String, String> response = new HashMap<>();
        response.put("message", todoId + "의 completed가 정상적으로 토글되었습니다");

        return ResponseEntity.ok(new HashMap<>(response));
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<Map<String, String>> DeleteTodo(@PathVariable("todoId") Long todoId) {
        log.info("DeleteTodo: {}", todoId);
        crudRepository.deleteTodo(todoId);

        Map<String, String> response = new HashMap<>();
        response.put("message", todoId + "의 todo가 삭제되었습니다");

        return ResponseEntity.ok(new HashMap<>(response));
    }
}