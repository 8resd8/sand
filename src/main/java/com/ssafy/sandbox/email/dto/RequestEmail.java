package com.ssafy.sandbox.email.dto;

import jakarta.validation.constraints.Email;


public final class RequestEmail {

    @Email
    private String email;

    public RequestEmail() {
    }

    public RequestEmail(String email) {
        this.email = email;
    }

    public @Email String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "RequestEmail{" +
                "email='" + email + '\'' +
                '}';
    }
}
