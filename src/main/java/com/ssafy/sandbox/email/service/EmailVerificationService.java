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

    private static final String EMAIL_KEY_SUFFIX = ":email";  // 코드로 이메일을 조회할 때 사용

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

    public void storeVerificationCode(String email, String code) {
        log.info("이메일 및 인증코드 저장: 이메일: {}, 코드: {}", email, code);
        // 이메일 -> 코드 저장, key - value, 지속시간
        redisTemplate.opsForValue().set(email, code, Duration.ofMinutes(EXPIRATION_TIME));
    }

    public boolean verifyCode(String email, String authCode) {
        // 이메일로 저장된 인증 코드 조회
        String storedCode = redisTemplate.opsForValue().get(email);
        if (storedCode != null && !storedCode.equals(authCode)) {
            log.info("인증 실패: 이메일: {}, 저장된 코드: {}, 입력된 코드: {}", email, storedCode, authCode);
            return false;
        }

        // 인증 성공 시 저장되어 있던 이메일 삭제
        log.info("인증 성공: 이메일: {}, 코드: {}", email, authCode);
        redisTemplate.delete(email);
        return true;
    }
}