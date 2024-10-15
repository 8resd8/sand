package com.ssafy.sandbox.paging.dto;

import java.util.List;

public record ResponsePaging(String message, int currentPageNumber,
                             int size, boolean hasNext,
                             List<ResponsePaging> todos) {
// 접근자와 생성자, toString, equals, hashCode

    public ResponsePaging {
    }
}
