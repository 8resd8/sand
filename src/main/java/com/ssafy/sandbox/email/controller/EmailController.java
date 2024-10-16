package com.ssafy.sandbox.email.controller;

import com.ssafy.sandbox.email.dto.RequestEmail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@Slf4j
public class EmailController {

    @PostMapping("/email")
    public ResponseEntity<HashMap<String, Object>> getEmail(@Validated @RequestBody RequestEmail requestEmail, BindingResult bindingResult) {
        log.info("입력 Email: {}", requestEmail);
        HashMap<String, Object> response = new HashMap<>();

        if (bindingResult.hasErrors()) {
            log.info("에러발생: {}", bindingResult);
            response.put("message", "이메일 인증에 실패했습니다.");
            return ResponseEntity.badRequest().body(response);
        }
        response.put("isOk", true);

        return ResponseEntity.ok(response);
    }
}