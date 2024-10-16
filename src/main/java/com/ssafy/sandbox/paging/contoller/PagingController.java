package com.ssafy.sandbox.paging.contoller;

import com.ssafy.sandbox.crud.dto.ResponseTodo;
import com.ssafy.sandbox.paging.dto.RequestCursor;
import com.ssafy.sandbox.paging.dto.ResponseCursor;
import com.ssafy.sandbox.paging.service.PagingService;
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

    private final PagingService offsetService;

    // cursor: 스크롤 내리면 알아서 다음페이지 요청 들어온다.
    @GetMapping("/cursor")
    @ResponseStatus(HttpStatus.OK)
    public ResponseCursor cursorPaging(@ModelAttribute RequestCursor requestCursor) {
        log.info("offsetPaging: {}", requestCursor);

        List<ResponseTodo> pageTotos = offsetService.pagedTotos(requestCursor.size(), requestCursor.cursorId());
        Long lastId = offsetService.getNextCursor(pageTotos);
        log.info("controller lastId: {}", lastId);
        boolean hasNext = offsetService.hasNext(requestCursor.cursorId(), requestCursor.size());

        ResponseCursor responseCursor = new ResponseCursor("정상적으로 요청되었습니다.", lastId, requestCursor.size(), hasNext, pageTotos);
        log.info("responsePaging: {}", responseCursor);

        return responseCursor;
    }
}