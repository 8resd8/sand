package com.ssafy.sandbox.paging.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class PageRequest {

    private List<PageDto> articles;

    public PageRequest(List<PageDto> articles) {
        this.articles = articles;
    }
}
