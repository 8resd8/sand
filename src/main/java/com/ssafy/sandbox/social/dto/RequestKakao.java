package com.ssafy.sandbox.social.dto;

import lombok.*;

@Getter
@ToString
public class RequestKakao {
    private String client_id;
    private String redirect_uri;
    private final String response_type = "code"; // code로 고정값
    private String scope;
    private String prompt;
    private String login_hint;
    private String service_terms;
    private String state;
    private String nonce;

    public RequestKakao() {
    }

    public RequestKakao(String client_id, String redirect_uri, String scope, String prompt, String login_hint, String service_terms, String state, String nonce) {
        this.client_id = client_id;
        this.redirect_uri = redirect_uri;
        this.scope = scope;
        this.prompt = prompt;
        this.login_hint = login_hint;
        this.service_terms = service_terms;
        this.state = state;
        this.nonce = nonce;
    }
}
