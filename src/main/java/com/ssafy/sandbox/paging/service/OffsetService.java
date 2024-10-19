package com.ssafy.sandbox.paging.service;

import com.ssafy.sandbox.domain.Todo;
import com.ssafy.sandbox.crud.service.CrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OffsetService  {

    private final CrudService crudService;

    public OffsetService(CrudService crudService) {
        this.crudService = crudService;
    }

    public List<Todo> pagedTodos(int size, int page) {
        int offset = (page - 1) * size; // limit 개수 OFFSET 시작지점
        List<Todo> todos = crudService.offsetPaging(size, offset);

        return todos;
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
        int totalData = crudService.getTotalCount();  // 총 데이터 개수
        return (int) Math.ceil((double) totalData / size);  // 총 페이지 수 계산, 올림
    }
}