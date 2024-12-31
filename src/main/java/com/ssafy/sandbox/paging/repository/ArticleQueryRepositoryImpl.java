package com.ssafy.sandbox.paging.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.sandbox.paging.dto.PageDto;
import com.ssafy.sandbox.paging.dto.QPageDto;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.ssafy.sandbox.paging.entity.QArticle.article;

@RequiredArgsConstructor
public class ArticleQueryRepositoryImpl implements ArticleQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<PageDto> findByCursor(Long cursorId, int size) {
        return queryFactory
                .select(new QPageDto(
                        article.id,
                        article.title,
                        article.createdAt))
                .from(article)
                .where(article.id.gt(cursorId))
                .orderBy(article.id.asc())
                .limit(size)
                .fetch();
    }

    @Override
    public long getLastId(Long cursorId, List<PageDto> result) {
        return result.isEmpty() ? cursorId : result.get(result.size() - 1).getId();
    }
}
