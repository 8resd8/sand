package com.ssafy.sandbox.email.controller;

import com.ssafy.sandbox.email.dto.RequestEmail;
import com.ssafy.sandbox.email.service.EmailSendService;
import com.ssafy.sandbox.email.service.EmailVerificationService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class EmailController {

    private final EmailSendService emailSendService;
    private final EmailVerificationService emailVerificationService

    @PostMapping("/email")
    public ResponseEntity<?> sendCodeToEmail(@Validated @RequestBody RequestEmail requestEmail, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("잘못된 요청: {}", bindingResult.getAllErrors());
            return ResponseEntity.badRequest().body("잘못된 이메일 형식입니다.");
        }

        // 인증 코드 생성 및 Redis에 저장
        String verificationCode = emailVerificationService.generateVerificationCode();
        emailVerificationService.storeVerificationCode(requestEmail.getEmail(), verificationCode);

        // 이메일 발송
        emailSendService.sendEmailForm(requestEmail.getEmail(), "이메일 인증 코드", "인증 코드: " + verificationCode);

        // 인증코드를 비교하는 서비스 로직필요

        HashMap<String, Object> response = new HashMap<>();
        response.put("message", "이메일이 성공적으로 전송되었습니다.");



        return ResponseEntity.ok(response);
    }
}