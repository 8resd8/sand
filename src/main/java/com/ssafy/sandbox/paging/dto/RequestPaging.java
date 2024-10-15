package com.ssafy.sandbox.paging.dto;

public record RequestPaging(int size, int page) {
}

//@ToString
//@Getter
//public class RequestPaging {
//    private int size;
//    private int page;
//
//    public RequestPaging() {
//    }
//
//    public RequestPaging(int size, int page) {
//        this.size = size;
//        this.page = page;
//    }
//}
