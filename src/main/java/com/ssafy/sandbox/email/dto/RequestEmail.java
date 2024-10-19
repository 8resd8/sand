package com.ssafy.sandbox.email.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RequestEmail {

    @Email
    private String email;

    public RequestEmail() {
    }

    public RequestEmail(String email) {
        this.email = email;
    }
}
