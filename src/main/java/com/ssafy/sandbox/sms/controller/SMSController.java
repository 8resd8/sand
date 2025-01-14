package com.ssafy.sandbox.sms.controller;

import com.ssafy.sandbox.sms.dto.SMSRequest;
import com.ssafy.sandbox.sms.dto.SMSVerifyRequest;
import com.ssafy.sandbox.sms.service.SMSService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sms")
public class SMSController {

    private final SMSService smsService;

    @PostMapping("/send")
    @ResponseStatus(HttpStatus.OK)
    public void send(@RequestBody SMSRequest request) {
        smsService.sendSMS(request);
    }

    @PostMapping("verify")
    @ResponseStatus(HttpStatus.OK)
    public void verify(@RequestBody SMSVerifyRequest request) {
        smsService.verifyAuthCode(request);
    }
}
