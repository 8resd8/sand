package com.ssafy.sandbox.paging.dto.v0;

import java.util.List;

public record ResponseCursor(Long lastId,
                             int size, boolean hasNext,
                             List<Paging> articles) {
}