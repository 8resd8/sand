package com.ssafy.sandbox.sms.exception;

public class SMSVerificationFailedException extends RuntimeException {
    public SMSVerificationFailedException(String message) {
        super(message);
    }
}
