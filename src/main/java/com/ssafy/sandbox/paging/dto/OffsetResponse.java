package com.ssafy.sandbox.paging.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class OffsetResponse {

    private List<PageDto> articles = new ArrayList<>();
    private long totalPage;


    public OffsetResponse(List<PageDto> offsetPage, long totalPage) {
        this.articles = offsetPage;
        this.totalPage = totalPage;
    }
}
