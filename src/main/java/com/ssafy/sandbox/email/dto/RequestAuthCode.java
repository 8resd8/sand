package com.ssafy.sandbox.email.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RequestAuthCode {

    @NotBlank
    private String authentication;

    public RequestAuthCode() {
    }

    public RequestAuthCode(String authentication) {
        this.authentication = authentication;
    }
}
