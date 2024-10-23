package com.ssafy.sandbox.email.controller;

import com.ssafy.sandbox.email.dto.RequestAuthCode;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailController {

    private final EmailSendService emailSendService;
    private final EmailVerificationService emailVerificationService;

    @PostMapping
    public ResponseEntity<?> sendCodeToEmail(@Validated @RequestBody RequestEmail requestEmail, BindingResult bindingResult) {
        HashMap<String, Object> response = new HashMap<>();
        log.info("userEmail: {}", requestEmail);

        if (bindingResult.hasErrors()) {
            log.error("잘못된 요청: {}", bindingResult.getAllErrors());
            return ResponseEntity.badRequest().body("잘못된 이메일 형식입니다.");
        }

        // 인증 코드 생성 및 Redis에 저장
        String authCode = emailVerificationService.generateVerificationCode();
        emailVerificationService.storeVerificationCode(requestEmail.getEmail(), authCode);

        // 이메일 발송
        emailSendService.sendEmailForm(requestEmail.getEmail(), authCode);

        response.put("isOk", true);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/authentication")
    public ResponseEntity<?> authCodeVerification(@Validated @RequestBody RequestAuthCode authCode, BindingResult bindingResult) {
        HashMap<String, Object> response = new HashMap<>();
        log.info("입력받은 사용자 code: {}", authCode);

        if (bindingResult.hasErrors()) {
            log.error("잘못된 요청: {}", bindingResult.getAllErrors());
            return ResponseEntity.badRequest().body("잘못된 요청입니다.");
        }

        // 인증 코드를 비교
        boolean isSuccess = emailVerificationService.verifyCode(authCode.getAuthentication());

        // 인증 성공
        if (isSuccess) {
            response.put("isSuccess", true);
            return ResponseEntity.ok().body(response);
        }

        // 인증 실패
        response.put("message", "잘못된 접근입니다.");
        return ResponseEntity.badRequest().body(response);
    }
}