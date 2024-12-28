package com.ssafy.sandbox.paging.dto.v0;

import java.util.List;

public record ResponseOffset(int currentPageNumber,
                             int size, int totalPage,
                             boolean hasNext, boolean hasPrevious,
                             List<Paging> articles) {
}