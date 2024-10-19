package com.ssafy.sandbox.paging.contoller;

import com.ssafy.sandbox.paging.dto.*;
import com.ssafy.sandbox.paging.service.CursorService;
import com.ssafy.sandbox.paging.service.OffsetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/articles/paging")
public class PagingController {

    private final CursorService cursorService;
    private final OffsetService offsetService;

    // cursor: 스크롤 내리면 알아서 다음페이지 요청 들어온다.
    @GetMapping("/cursor")
    @ResponseStatus(HttpStatus.OK)
    public ResponseCursor cursorPaging(@ModelAttribute RequestCursor requestCursor) {
        log.info("Cursor Input Data: {}", requestCursor);

        List<Paging> pages = cursorService.pagedTotos(requestCursor.size(), requestCursor.cursorId());
        Long lastId = cursorService.getNextCursor(pages);
        boolean hasNext = cursorService.hasNext(requestCursor.cursorId(), requestCursor.size());

        ResponseCursor responseCursor = new ResponseCursor(lastId, requestCursor.size(), hasNext, pages);
        log.info("Cursor Response Data: {}", responseCursor);

        return responseCursor;
    }


    @GetMapping("/offset")
    @ResponseStatus(HttpStatus.OK)
    public ResponseOffset offsetPaging(@ModelAttribute RequestOffset requestOffset) {
        log.info("Offset Input Data: {}", requestOffset);
        int page = requestOffset.page();
        int size = requestOffset.size();

        int currentPageNumber = offsetService.currentPageNumber(page);
        int totalPage = offsetService.totalPage(size);
        boolean hasNext = offsetService.hasNext(size, page);
        boolean hasPrevious = offsetService.hasPrevious(page);
        List<Paging> todos = offsetService.pagedTodos(size, page);
        ResponseOffset responseDate = new ResponseOffset(currentPageNumber,
                size, totalPage, hasNext, hasPrevious, todos);

        log.info("Offset Response Data: {}", responseDate);
        return responseDate;
    }
}