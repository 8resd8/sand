package com.ssafy.sandbox.crud.dto;

// DB의 값을 조회해서 저장하는 클래스
public record Todo (Long id, String content, boolean completed){
}
