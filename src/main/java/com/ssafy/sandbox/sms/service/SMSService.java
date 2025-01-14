package com.ssafy.sandbox.sms.service;

import com.ssafy.sandbox.sms.dto.SMSRequest;
import com.ssafy.sandbox.sms.dto.SMSVerifyRequest;
import com.ssafy.sandbox.sms.exception.SMSVerificationFailedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.HashMap;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class SMSService {

    @Value("${coolsms.api.key}")
    private String apiKey;
    @Value("${coolsms.api.secret}")
    private String apiSecret;
    @Value("${coolsms.api.number}")
    private String fromPhoneNumber;

    private final RedisTemplate<String, String> redisTemplate;
    private final long SMS_TIMEOUT = 300; // 만료시간 5분

    public void sendSMS(SMSRequest request) {
        Message coolsms = new Message(apiKey, apiSecret);

        HashMap<String, String> sendSMS = sendMessage(request.toPhoneNumber());
        try {
            coolsms.send(sendSMS);
        } catch (CoolsmsException e) {
            throw new IllegalArgumentException("메시지 전송 실패", e);
        }
    }

    private HashMap<String, String> sendMessage(String toPhoneNumber) {
        String randomNumber6 = generateNumber();
        log.info("저장된 SMS 인증번호: {}", randomNumber6);
        redisTemplate.opsForValue().set(toPhoneNumber, randomNumber6, Duration.ofSeconds(SMS_TIMEOUT));

        HashMap<String, String> sendSMS = new HashMap<>();
        sendSMS.put("to", toPhoneNumber);
        sendSMS.put("from", fromPhoneNumber);
        sendSMS.put("type", "sms");
        sendSMS.put("text", "인증번호는 [" + randomNumber6 + "] 입니다.");

        return sendSMS;
    }

    private String generateNumber() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }


    public void verifyAuthCode(SMSVerifyRequest request) {
        String authCode = redisTemplate.opsForValue().get(request.toPhoneNumber());
        log.info("전화번호: {}, 저장된 인증코드: {}", request.toPhoneNumber(), authCode);

        if (authCode == null || !request.authCode().equals(authCode)) {
            throw new SMSVerificationFailedException("인증코드가 일치하지 않거나 만료되었습니다.");
        }
        redisTemplate.delete(request.toPhoneNumber());
    }
}
