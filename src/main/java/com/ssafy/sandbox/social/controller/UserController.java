package com.ssafy.sandbox.social.controller;

import com.ssafy.sandbox.social.dto.*;
import com.ssafy.sandbox.social.service.OauthService;
import com.ssafy.sandbox.social.service.UserService;
import com.ssafy.sandbox.social.util.JWTErrorCode;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/oauth/authorization")
public class UserController {

    private final OauthService oauthService;
    private final UserService userService;

    @PostMapping("/auth")
    public ResponseEntity<?> signIn(@RequestBody LoginRequest dto) {
        if (dto.getCode() == null) {
            return ResponseEntity.badRequest().build();
        }

        log.debug("code: {}", dto.getCode());
        String accessToken = oauthService.getAccessToken(dto.getCode());
        log.debug("access token: {}", accessToken);
        KakaoUserInfoResponse userInfo = oauthService.getUserInfo(accessToken);
        LoginResponse result = userService.getToken(userInfo);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/member")
    public ResponseEntity<?> getNickname(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String token) {
        if (token == null) {
            return ResponseEntity.badRequest().build();
        }
        log.debug("token: {}", token);
        try {
            VerifyTokenResponse response = userService.getNickname(token);
            return ResponseEntity.ok().body(response);
        } catch (JwtException e) {
            if (e.getMessage().equals(JWTErrorCode.INVALID_TOKEN.getMessage())) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/reissue")
    public ResponseEntity<?> reissue(
            @RequestHeader(value = "X-Refresh", required = false) String token) {
        if (token == null) {
            return ResponseEntity.badRequest().build();
        }

        log.debug("token: {}", token);
        try {
            ReIssueToken response = userService.reissueToken(token);
            return ResponseEntity.ok().body(response);
        } catch (JwtException e) {
            if (e.getMessage().equals(JWTErrorCode.INVALID_TOKEN.getMessage())) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}