package com.ssafy.sandbox.paging.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.sandbox.paging.dto.PageDto;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.ssafy.sandbox.paging.entity.QArticle.article;

@RequiredArgsConstructor
public class ArticleQueryRepositoryImpl implements ArticleQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<PageDto> findByCursor(Long cursorId, int size) {
        return queryFactory
                .select(Projections.constructor(PageDto.class,
                        article.id,
                        article.title,
                        article.createdAt))
                .from(article)
                .where(cursorCondition(cursorId))
                .orderBy(article.id.asc())
                .limit(size)
                .fetch();
    }

    private BooleanExpression cursorCondition(Long cursorId) {
        return article.id.gt(cursorId);
    }
}
