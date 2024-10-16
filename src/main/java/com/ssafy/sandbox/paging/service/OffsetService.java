package com.ssafy.sandbox.paging.service;

import com.ssafy.sandbox.crud.dto.ResponseTodo;
import com.ssafy.sandbox.crud.service.CrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OffsetService implements PagingService {

    private final CrudService crudService;

    public OffsetService(CrudService crudService) {
        this.crudService = crudService;
    }

    /**
     * 현재 페이지의 시작 인덱스 계산
     */
    public int startPage(int size, int page) {
        return size * page;
    }

    /**
     * 현재 페이지의 맞게 인덱스 계산, 데이터 크기를 넘지 않도록 조정
     */
    @Override
    public int endPage(int size, int page, int todoSize) {
        log.info("endPage계산: size: {}, startPage: {}, todoSize: {}", size, page, todoSize);
        return Math.min((page + 1) * size, todoSize);
    }

    /**
     * 사이즈에 맞는 인덱스 만큼 잘라서 보여준다.
     */
    public List<ResponseTodo> pagedTotos(int size, int page) {
        List<ResponseTodo> todos = crudService.findAll();
        int start = startPage(size, page);
        int end = endPage(size, page, todos.size());
        log.info("startPage: {}, endPage: {}, todosSize: {}", start, end, todos.size());
        return todos.subList(start, end);
    }

    /**
     * 다음 페이지가 있는지 확인
     */
    @Override
    public boolean hasNext(int size, int page) {
        int todoSize = getTodoSize();
        int end = endPage(size, page, todoSize);
        log.info("end: {}, todoSize: {}, hasNext: {}", end, todoSize, end < todoSize);
        return end < todoSize;
    }

    /**
     * 전체 todo 목록
     */
    private int getTodoSize() {
        return crudService.findAll().size();
    }
}