package com.ssafy.sandbox.social.controller;

import com.ssafy.sandbox.social.dto.RequestKakao;
import com.ssafy.sandbox.social.dto.RequestTest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/oauth")
public class KakaoController {

    @PostMapping("/auth")
    public ResponseEntity<?> auth2(@RequestBody RequestTest requestTest) {
        log.info("post auth: {}", requestTest);
        HashMap<String, Object> response = new HashMap<>();
//        response.put("accessToken", )
        return ResponseEntity.ok(requestTest);
    }

    @GetMapping("/cookie/auth")
    public ResponseEntity<?> authq(@RequestBody Map<String, Object> code) {
        log.info("get auth: {}", code);
        return ResponseEntity.ok(code);
    }


}
