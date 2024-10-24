package com.ssafy.sandbox.social.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ResponseKakao {
    private String code;
    private String error;
    private String error_descroption;
    private String status;

    public ResponseKakao() {
    }

    public ResponseKakao(String code, String error, String error_descroption, String status) {
        this.code = code;
        this.error = error;
        this.error_descroption = error_descroption;
        this.status = status;
    }
}
