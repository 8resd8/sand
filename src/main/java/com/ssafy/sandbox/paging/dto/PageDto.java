package com.ssafy.sandbox.paging.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.ssafy.sandbox.paging.entity.Article;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PageDto {

    private Long id;
    private String title;
    private LocalDateTime createdAt;

    @QueryProjection
    public PageDto(Long id, String title, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.createdAt = createdAt;
    }

    public PageDto(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.createdAt = article.getCreatedAt();
    }
}
