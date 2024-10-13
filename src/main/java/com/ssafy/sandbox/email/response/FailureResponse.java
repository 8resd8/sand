package com.ssafy.sandbox.email.response;

public class FailureResponse {

    private final String massage;

    public FailureResponse(String massage) {
        this.massage = massage;
    }

    public String getMassage() {
        return massage;
    }
}
