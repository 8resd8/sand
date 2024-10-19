package com.ssafy.sandbox.paging.dto;

import java.util.List;

public record ResponseCursor(Long lastId,
                             int size, boolean hasNext,
                             List<Paging> articles) {
}