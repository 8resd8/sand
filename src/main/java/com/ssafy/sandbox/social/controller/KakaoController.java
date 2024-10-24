package com.ssafy.sandbox.social.controller;

import com.ssafy.sandbox.social.dto.RequestKakao;
import com.ssafy.sandbox.social.dto.RequestTest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
public class KakaoController {

    //    @PostMapping("/oauth/auth")
    public ResponseEntity<?> auth(@RequestParam("code") String code) {
        log.info("post auth: {}", code);

        return ResponseEntity.ok(code);
    }

    @PostMapping("/oauth/auth")
    public ResponseEntity<?> auth2(@ModelAttribute RequestTest code) {
        log.info("post auth: {}", code);

        return ResponseEntity.ok(code);
    }

    @GetMapping("/oauth/auth")
    public ResponseEntity<?> authq(@RequestBody Map<String, Object> code) {
        log.info("get auth: {}", code);
        return ResponseEntity.ok(code);
    }

    @GetMapping("/callback")
    public ResponseEntity<?> authorize(@RequestParam("code") String code) {
        log.info("authorize: {}", code);
        return ResponseEntity.ok(code);
    }
}
