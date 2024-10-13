package com.ssafy.sandbox.email.response;

public class SuccessResponse {

    private final boolean isOk;

    public SuccessResponse(boolean isOk) {
        this.isOk = isOk;
    }

    public boolean getIsOk() {
        return isOk;
    }
}
