package com.ssafy.sandbox.paging.repository;

import com.ssafy.sandbox.paging.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PageRepository extends JpaRepository<Article, Long> {
    }
