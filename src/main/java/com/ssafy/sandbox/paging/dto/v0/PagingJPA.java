package com.ssafy.sandbox.paging.dto.v0;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "paging") // 이름이 같으면 생략이 가능함
public class PagingJPA {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // mysql의 자동증가
    private Long id;
    private String title;
    private Timestamp createdAt;

    public PagingJPA() { // JPA 기본 생성자 필수
    }

    public PagingJPA(Long id, String title, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.createdAt = createdAt;
    }
}
