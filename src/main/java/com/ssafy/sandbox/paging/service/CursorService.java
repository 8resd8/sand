package com.ssafy.sandbox.paging.service;

import com.ssafy.sandbox.crud.dto.ResponseTodo;
import com.ssafy.sandbox.crud.service.CrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CursorService  {

    private final CrudService crudService;

    public CursorService(CrudService crudService) {
        this.crudService = crudService;
    }

    /**
     * 커서를 기준으로 데이터 조회한다
     */
    public List<ResponseTodo> pagedTotos(int size, Long cursorId) {
        // 첫 요청이면 그대로 size 만큼 돌려주기
        if (cursorId == 0) {
            return crudService.cursorPaging(0L, size);
        }

        List<ResponseTodo> todos = crudService.cursorPaging(cursorId, size + 1);
        if (todos.size() > size) todos.remove(todos.size() - 1);

        log.info("cursorId: {}, todosSize: {}", cursorId, todos.size());
        return todos;
    }

    /**
     * 다음 페이지가 있는지 확인
     */

    public boolean hasNext(Long cursorId, int size) {
        return crudService.cursorPaging(cursorId, size + 1).size() > size;
    }


    public Long getNextCursor(List<ResponseTodo> todos) {
        if (todos.isEmpty()) return 0L;

        // 마지막 데이터 ID 반환, 다음 페이지 커서로 사용
        log.info("lastCursor: {}", todos.get(todos.size() - 1).getId());
        return todos.get(todos.size() - 1).getId();
    }
}