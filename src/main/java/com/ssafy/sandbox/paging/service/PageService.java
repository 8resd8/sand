package com.ssafy.sandbox.paging.service;

import com.ssafy.sandbox.paging.dto.CursorResponse;
import com.ssafy.sandbox.paging.dto.OffsetResponse;
import com.ssafy.sandbox.paging.dto.PageDto;
import com.ssafy.sandbox.paging.dto.PageRequest;
import com.ssafy.sandbox.paging.entity.Article;
import com.ssafy.sandbox.paging.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PageService {

    private final ArticleRepository articleRepository;


    public OffsetResponse getOffset(Pageable pageable) {
        Page<PageDto> offsetPage = articleRepository.findAll(pageable).map(PageDto::new);

        return new OffsetResponse(offsetPage.getContent(), offsetPage.getTotalPages() - 1);
    }


    public CursorResponse getCursor(Long cursorId, int size) {
        List<PageDto> cursorPage = articleRepository.findByCursor(cursorId, size);

        return new CursorResponse(cursorPage, cursorPage.size());
    }

    public void saveAll(PageRequest request) {
        List<Article> articles = request.getArticles().stream()
                .map(dto -> new Article(dto.getId(), dto.getTitle(), dto.getCreatedAt()))
                .toList();

        articleRepository.saveAll(articles);
    }
}
