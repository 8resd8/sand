package com.ssafy.sandbox.social.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginResponse {

    String accessToken;
    String refreshToken;

    @Builder
    public LoginResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}