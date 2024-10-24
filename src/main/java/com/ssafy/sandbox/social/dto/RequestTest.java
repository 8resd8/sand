package com.ssafy.sandbox.social.dto;

import lombok.Data;
import lombok.Getter;

@Data
public class RequestTest {
    private String response_type;
    private String  client_id;
    private String  redirect_uri;
}
