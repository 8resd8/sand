package com.ssafy.sandbox.sms.service;

import com.ssafy.sandbox.sms.dto.SMSRequest;
import com.ssafy.sandbox.sms.dto.SMSVerifyRequest;
import com.ssafy.sandbox.sms.exception.SMSVerificationFailedException;
import net.nurigo.java_sdk.api.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.time.Duration;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class SMSServiceTest {

    @InjectMocks
    private SMSService smsService;

    @Mock
    private RedisTemplate<String, String> redisTemplate;

    @Mock
    private ValueOperations<String, String> valueOperations; // redis key-value 인터페이스

    @Mock
    private Message message;

    private final String toPhoneNumber = "01012341234";

    @Value("${coolsms.api.key}")
    private String apiKey;
    @Value("${coolsms.api.secret}")
    private String apiSecret;

    @BeforeEach
    void setUp() {
//        message = new Message(apiKey, apiSecret);
    }

//    @Test
//    void SMS_전송_성공() throws Exception {
//        // given
//        SMSRequest request = new SMSRequest(toPhoneNumber);
//
//        // RedisTemplate의 opsForValue() Mock 설정
//        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
//
//        // ValueOperations의 set 메서드 Mock 설정
//        doNothing().when(valueOperations).set(eq(toPhoneNumber), anyString(), eq(Duration.ofSeconds(300)));
//
//        // Mock Message API의 send 호출
//        doAnswer(invocation -> null).when(message).send(any(HashMap.class)); // send는 예외 없이 실행됨
//
//        // when
//        smsService.sendSMS(request);
//
//        // then
//        verify(message).send(any(HashMap.class)); // send 메서드가 호출되었는지 확인
//        verify(valueOperations).set(eq(toPhoneNumber), anyString(), eq(Duration.ofSeconds(300))); // Redis에 값 저장 확인
//    }





    @Test
    void SMS_인증성공() {
        SMSVerifyRequest request = new SMSVerifyRequest(toPhoneNumber, "123456");
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
        when(valueOperations.get(toPhoneNumber)).thenReturn("123456");

        smsService.verifyAuthCode(request);

        verify(redisTemplate).delete(toPhoneNumber);
    }


    @Test
    void SMS_인증실패() {
        SMSVerifyRequest request = new SMSVerifyRequest(toPhoneNumber, "wrongCode");
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
        when(valueOperations.get(toPhoneNumber)).thenReturn("123456"); // 값 세팅

        assertThrows(SMSVerificationFailedException.class, () -> smsService.verifyAuthCode(request));
        verify(redisTemplate, never()).delete(toPhoneNumber);
    }
}