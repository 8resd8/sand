package com.ssafy.sandbox.email.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class ResponseEmail {

    private String Message;
    @JsonProperty("isOk")
    private boolean isOk;

    public ResponseEmail() {
    }

    public ResponseEmail(String message) {
        Message = message;
    }

    public ResponseEmail(boolean isOk) {
        this.isOk = isOk;
    }
}
