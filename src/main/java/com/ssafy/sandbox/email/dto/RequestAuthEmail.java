package com.ssafy.sandbox.email.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RequestAuthEmail {

    @NotBlank
    private String authentication;
    private String email;

    public RequestAuthEmail() {
    }

    public RequestAuthEmail(String authentication, String email) {
        this.authentication = authentication;
        this.email = email;
    }
}
