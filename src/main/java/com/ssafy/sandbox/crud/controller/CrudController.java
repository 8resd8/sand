package com.ssafy.sandbox.crud.controller;

import com.ssafy.sandbox.crud.dto.RequestTodo;
import com.ssafy.sandbox.crud.dto.ResponseTodo;
import com.ssafy.sandbox.crud.dto.Todo;
import com.ssafy.sandbox.crud.service.CrudService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping({"/", "/todos"})
public class CrudController {

    private final CrudService crudService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> readTodo() {
        log.info("readTodo:");
        // 서비스에서 모든 Todo 항목을 조회
        List<Todo> findAll = crudService.findAll();

        // 조회된 Todo 리스트를 ResponseTodo로 변환
        List<ResponseTodo> todos = findAll.stream()
                .map(todo -> new ResponseTodo(todo.id(), todo.content(), todo.completed()))
                .collect(Collectors.toList());

        log.info("todos: {}", todos);

        HashMap<String, Object> response = new HashMap<>();
        response.put("message", "정상적으로 요청되었습니다.");
        response.put("todos", todos);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<HashMap<String, String>> createTodo(@Validated @RequestBody RequestTodo todos, BindingResult bindingResult) {
        log.info("createTodo Message: {}", todos);
        HashMap<String, String> response = new HashMap<>();

        if (bindingResult.hasErrors()) {
            log.info("값의 문제가 있음: {}", todos);
            response.put("message", "정상적이지 않은 요청입니다.");
            return ResponseEntity.badRequest().body(response);
        }

        response.put("message", todos.getContent());
        crudService.saveTodo(todos);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{todoId}")
    public ResponseEntity<Map<String, String>> UpdateTodo(@PathVariable Long todoId) {
        log.info("UpdateTodo id: {}", todoId);
        crudService.updateToggle(todoId);
        Map<String, String> response = new HashMap<>();
        response.put("message", todoId + "의 completed가 정상적으로 토글되었습니다");

        return ResponseEntity.ok(new HashMap<>(response));
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<Map<String, String>> DeleteTodo(@PathVariable Long todoId) {
        log.info("DeleteTodo id: {}", todoId);
        crudService.deleteTodo(todoId);

        Map<String, String> response = new HashMap<>();
        response.put("message", todoId + "의 todo가 삭제되었습니다");

        return ResponseEntity.ok(new HashMap<>(response));
    }
}