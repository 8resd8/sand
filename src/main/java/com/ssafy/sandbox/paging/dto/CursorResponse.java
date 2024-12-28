package com.ssafy.sandbox.paging.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Slice;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class CursorResponse {

    private List<PageDto> articles = new ArrayList<>();
    private long lastId;


    public CursorResponse(List<PageDto> cursorPage, long lastId) {
        this.articles = cursorPage;
        this.lastId = lastId;
    }
}
