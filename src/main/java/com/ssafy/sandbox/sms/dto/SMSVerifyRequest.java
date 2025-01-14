package com.ssafy.sandbox.sms.dto;

public record SMSVerifyRequest(String toPhoneNumber, String authCode) {
}
