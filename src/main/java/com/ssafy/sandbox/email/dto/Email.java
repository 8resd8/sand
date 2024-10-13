package com.ssafy.sandbox.email.dto;

import jakarta.validation.constraints.NotBlank;

public class Email {

    @jakarta.validation.constraints.Email
    @NotBlank(message = "이메일은 필수")
    private String email;

    public Email() {
    }

    public Email(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
