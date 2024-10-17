package com.ssafy.sandbox.paging.contoller;

import com.ssafy.sandbox.crud.dto.ResponseTodo;
import com.ssafy.sandbox.paging.dto.RequestCursor;
import com.ssafy.sandbox.paging.dto.RequestOffset;
import com.ssafy.sandbox.paging.dto.ResponseCursor;
import com.ssafy.sandbox.paging.dto.ResponseOffset;
import com.ssafy.sandbox.paging.service.CursorService;
import com.ssafy.sandbox.paging.service.OffsetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PagingController {

    private final CursorService cursorService;
    private final OffsetService offsetService;

    // cursor: 스크롤 내리면 알아서 다음페이지 요청 들어온다.
    @GetMapping("/paging/cursor")
    @ResponseStatus(HttpStatus.OK)
    public ResponseCursor cursorPaging(@ModelAttribute RequestCursor requestCursor) {
        log.info("cursorPaging: {}", requestCursor);

        List<ResponseTodo> pageTotos = cursorService.pagedTotos(requestCursor.size(), requestCursor.cursorId());
        Long lastId = cursorService.getNextCursor(pageTotos);
        log.info("controller lastId: {}", lastId);
        boolean hasNext = cursorService.hasNext(requestCursor.cursorId(), requestCursor.size());

        ResponseCursor responseCursor = new ResponseCursor("정상적으로 요청되었습니다.", lastId, requestCursor.size(), hasNext, pageTotos);
        log.info("responsePaging: {}", responseCursor);

        return responseCursor;
    }

    @GetMapping("/paging/offset")
    @ResponseStatus(HttpStatus.OK)
    public ResponseOffset offsetPaging(@ModelAttribute RequestOffset requestOffset) {
        log.info("offsetPaging: {}", requestOffset);
        int page = requestOffset.page();
        int size = requestOffset.size();
        int getPage = offsetService.currentPageNumber(page);
        List<ResponseTodo> todos = offsetService.pagedTodos(size, page);
        boolean hasNext = offsetService.hasPrevious(page);
        int getSize = offsetService.totalPage(size);


        log.info("{}, {}, {}, {}, {}", page, size, getPage, todos, getSize);


        return null;
    }
}