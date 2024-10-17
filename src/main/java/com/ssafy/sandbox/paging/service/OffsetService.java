package com.ssafy.sandbox.paging.service;

import com.ssafy.sandbox.crud.dto.ResponseTodo;
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

    /**
     * limit size offset 시작페이지
     */
    public List<ResponseTodo> pagedTodos(int size, int page) {
        // Offset 계산: 페이지 번호가 0부터 시작하므로 (page * size)
        int offset = page * size;
        List<ResponseTodo> todos = crudService.offsetPaging(size, offset);

        log.info("page: {}, offset: {}, todosSize: {}", page, offset, todos.size());
        return todos;
    }

    public boolean hasNext(int size, int page) {
        // 다음 페이지에 데이터가 있는지 확인하기 위해 (size + 1)만큼의 데이터를 요청
        int offset = (page + 1) * size;
        List<ResponseTodo> todos = crudService.offsetPaging(size, offset);

        // 다음 페이지가 존재하는지 여부 확인
        return todos.size() > 0;
    }

    public int currentPageNumber(int page) {
        // 현재 페이지 번호를 반환
        return page + 1; // 0부터 시작하므로 1을 더해 반환
    }

    public boolean hasPrevious(int page) {
        // 이전 페이지가 존재하는지 여부 확인
        return page > 0;
    }

    public int totalPage(int size) {
        // 전체 페이지 수를 계산하기 위해 전체 데이터 크기를 조회
        int totalItems = crudService.getTotalCount();  // 총 데이터 개수 조회 메서드 필요
        return (int) Math.ceil((double) totalItems / size);  // 총 페이지 수 계산
    }

    /**
     * 주어지는 입력: size = 10, page = 0, 1, 2 ...
     * 필요한 것들
     *   "currentPageNumber": 1,
     *   "size": 10,
     *   "totalPage": 5,
     *   "hasNext": true,
     *   "hasPrevious": false,
     */


}