package com.ssafy.sandbox.email.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Duration;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailVerificationService {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom random = new SecureRandom();
    private static final long EXPIRATION_TIME = 5;  // 인증 코드의 만료 시간 (분)

    private final StringRedisTemplate redisTemplate;

    // 6자리 무작위 인증코드 생성
    public String generateVerificationCode() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }
        return sb.toString();
    }

    // Redis에 인증 코드 저장
    public void storeVerificationCode(String email, String code) {
        redisTemplate.opsForValue().set(email, code, Duration.ofMinutes(EXPIRATION_TIME));
    }

    // 인증 코드 검증
    public boolean verifyCode(String email, String code) {
        String storedCode = redisTemplate.opsForValue().get(email);

        if (storedCode != null && storedCode.equals(code)) {
            redisTemplate.delete(email);  // 인증 성공 시 인증 코드 삭제
            return true;
        }
        return false;
    }
}