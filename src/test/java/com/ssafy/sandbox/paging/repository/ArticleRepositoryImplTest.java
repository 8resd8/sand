package com.ssafy.sandbox.paging.repository;

import com.ssafy.sandbox.paging.dto.PageDto;
import com.ssafy.sandbox.paging.entity.Article;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ArticleRepositoryImplTest {

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    void setUp() {
        // 데이터 삭제
        articleRepository.deleteAll();

        // Auto Increment 초기화
        entityManager.createNativeQuery("ALTER TABLE article AUTO_INCREMENT = 1").executeUpdate();

        List<Article> articles = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            articles.add(new Article("제목" + i));
        }
        articleRepository.saveAllAndFlush(articles);
    }


    @Test
    void findByCursor() {
        List<Article> findAll = articleRepository.findAll();
        List<PageDto> emptyPage = articleRepository.findByCursor(findAll.get(findAll.size() - 1).getId(), 10);
        List<PageDto> lastPage = articleRepository.findByCursor(findAll.get(findAll.size() - 2).getId(), 10);

        assertThat(emptyPage).isEmpty();
        assertThat(lastPage.size()).isEqualTo(1);
    }

    @Test
    void lastId() {
        List<Article> findAll = articleRepository.findAll();
        List<PageDto> lastPage = articleRepository.findByCursor(findAll.get(findAll.size() - 2).getId(), 10);

        Long lastPageId = lastPage.get(lastPage.size() - 1).getId();
        long actualLastId = articleRepository.getLastId(lastPageId, lastPage);

        assertThat(actualLastId).isEqualTo(lastPageId);
    }

}