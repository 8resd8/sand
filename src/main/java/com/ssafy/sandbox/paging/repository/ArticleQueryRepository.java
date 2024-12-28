package com.ssafy.sandbox.paging.repository;

import com.ssafy.sandbox.paging.dto.PageDto;

import java.util.List;

public interface ArticleQueryRepository {
    List<PageDto> findByCursor(Long cursorId, int size);

    long getLastId(Long cursorId, List<PageDto> result);
}
