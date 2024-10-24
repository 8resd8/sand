package com.ssafy.sandbox.paging.service;

import com.ssafy.sandbox.paging.dto.Paging;
import com.ssafy.sandbox.paging.repository.MysqlPagingRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OffsetService  {

    private final MysqlPagingRepository mysqlPagingRepository;

    public OffsetService(MysqlPagingRepository mysqlPagingRepository) {
        this.mysqlPagingRepository = mysqlPagingRepository;
    }

    public List<Paging> pagedTodos(int size, int page) {
        int offset = (page - 1) * size; // limit 개수 OFFSET 시작지점
        return mysqlPagingRepository.offsetPaging(size, offset);
    }

    public int currentPageNumber(int page) {
        return page; // 1부터 시작
    }

    public boolean hasPrevious(int page) {
        return page > 0; // 이전 페이지
    }

    public boolean hasNext(int size, int page) {
        return (page - 1) < totalPage(size); // 다음 페이지
    }

    public int totalPage(int size) {
        int totalData = mysqlPagingRepository.getTotalCount();  // 총 데이터 개수
        return (int) Math.ceil((double) totalData / size);  // 총 페이지 수 계산, 올림
    }
}