package com.ssafy.sandbox.crud.controller;

import com.ssafy.sandbox.crud.dto.RequestTodo;
import com.ssafy.sandbox.crud.dto.ResponseTodo;
import com.ssafy.sandbox.crud.service.CrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping({"/", "/todos"})
public class CrudController {

    private final CrudService crudService;

    public CrudController(@Qualifier("myBatis") CrudService crudService) {
        this.crudService = crudService;
    }


    @GetMapping
    public ResponseEntity<?> readTodo() {
        String massage = "정상적으로 요청되었습니다.";

        // 에러가 날 경우 메시지 변경
        ResponseTodo response = new ResponseTodo(massage, crudService.findAll());
        log.info("Read response: {}", response);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<HashMap<String, String>> createTodo(@Validated @RequestBody RequestTodo todos, BindingResult bindingResult) {
        log.info("createTodo: {}", todos);
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
    public ResponseEntity<Map<String, Object>> UpdateTodo(@PathVariable Long todoId) {
        log.info("UpdateTodo id: {}", todoId);
        crudService.updateToggle(todoId);
        Map<String, Object> response = new HashMap<>();
        response.put("message", todoId + "의 completed가 정상적으로 토글되었습니다");

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<Map<String, Object>> DeleteTodo(@PathVariable Long todoId) {
        log.info("DeleteTodo id: {}", todoId);
        crudService.deleteTodo(todoId);

        Map<String, Object> response = new HashMap<>();
        response.put("message", todoId + "의 todo가 삭제되었습니다");

        return ResponseEntity.ok(response);
    }
}