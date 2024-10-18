package com.ssafy.sandbox.email.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailSendService {

    private final JavaMailSender javaMailSender;

    /// 발신할 이메일 설정
    public void sendEmailForm(String toEmail, String title, String content) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setSubject(title);
            message.setText(content);
            message.setFrom("본인 구글 이메일@gmail.com");  // 발신자 이메일 설정

            javaMailSender.send(message);
            log.info("이메일 발송 성공: {}", message);
        } catch (Exception e) {
            log.error("이메일 발송 실패", e);
            throw new RuntimeException("이메일 발송에 실패했습니다.");
        }
    }

}