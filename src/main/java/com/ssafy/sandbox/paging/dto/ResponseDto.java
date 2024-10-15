package com.ssafy.sandbox.paging.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ResponseDto {
    private int id;
    private String  content;
    private boolean completed;

    public ResponseDto() {
    }

    public ResponseDto(int id, String content, boolean completed) {
        this.id = id;
        this.content = content;
        this.completed = completed;
    }
}
