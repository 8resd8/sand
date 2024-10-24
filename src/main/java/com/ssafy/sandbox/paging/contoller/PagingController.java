package com.ssafy.sandbox.paging.contoller;

import com.ssafy.sandbox.paging.dto.*;
import com.ssafy.sandbox.paging.service.TotalPagingService;
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

    private final TotalPagingService totalPagingService;


    @GetMapping("/cursor")
    @ResponseStatus(HttpStatus.OK)
    public ResponseCursor cursorPaging(@ModelAttribute RequestCursor requestCursor) {
        log.info("Cursor Input Data: {}", requestCursor);

        List<Paging> pages = totalPagingService.pagedTotos(requestCursor.size(), requestCursor.cursorId());
        Long lastId = totalPagingService.getNextCursor(pages);
        boolean hasNext = totalPagingService.hasNext(requestCursor.cursorId(), requestCursor.size());

        ResponseCursor responseCursor = new ResponseCursor(lastId, requestCursor.size(), hasNext, pages);
        log.info("Cursor Response Cursor: {}", responseCursor);

        return responseCursor;
    }


    @GetMapping("/offset")
    @ResponseStatus(HttpStatus.OK)
    public ResponseOffset offsetPaging(@ModelAttribute RequestOffset requestOffset) {
        log.info("Offset Input Data: {}", requestOffset);
        int page = requestOffset.page();
        int size = requestOffset.size();

        int currentPageNumber = totalPagingService.currentPageNumber(page);
        int totalPage = totalPagingService.totalPage(size);
        boolean hasNext = totalPagingService.hasNext(size, page);
        boolean hasPrevious = totalPagingService.hasPrevious(page);
        List<Paging> todos = totalPagingService.pagedTodos(size, page);
        ResponseOffset responseOffset = new ResponseOffset(currentPageNumber,
                size, totalPage, hasNext, hasPrevious, todos);

        log.info("Offset Response Offset: {}", responseOffset);
        return responseOffset;
    }
}