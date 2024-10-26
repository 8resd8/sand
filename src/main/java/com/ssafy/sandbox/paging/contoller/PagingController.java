package com.ssafy.sandbox.paging.contoller;

import com.ssafy.sandbox.paging.dto.*;
import com.ssafy.sandbox.paging.service.TotalPagingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
public class PagingController {

    private final TotalPagingService totalPagingService;


    @GetMapping("/paging/cursor")
    @ResponseStatus(HttpStatus.OK)
    public ResponseCursor cursorPaging(@ModelAttribute RequestCursor requestCursor) {
        log.info("Cursor Input Data: {}", requestCursor);

        List<Paging> pages = totalPagingService.getCursorPaging(requestCursor.size(), requestCursor.cursorId());
        Long lastId = totalPagingService.getNextCursor(pages);
        boolean hasNext = totalPagingService.hasNext(requestCursor.cursorId(), requestCursor.size());

        ResponseCursor responseCursor = new ResponseCursor(lastId, requestCursor.size(), hasNext, pages);
        log.info("Cursor Response Cursor: {}", responseCursor);

        return responseCursor;
    }

    @GetMapping("/paging/offset")
    @ResponseStatus(HttpStatus.OK)
    public ResponseOffset offsetPaging(@ModelAttribute RequestOffset requestOffset) {
        log.info("Offset Input Data: {}", requestOffset);
        int page = requestOffset.page();
        int size = requestOffset.size();

        int currentPageNumber = totalPagingService.currentPageNumber(page);
        int totalPage = totalPagingService.totalPage(size);
        boolean hasNext = totalPagingService.hasNext(size, page);
        boolean hasPrevious = totalPagingService.hasPrevious(page);
        List<Paging> todos = totalPagingService.getOffsetPaging(size, page);
        log.info("offset todos 목록: {}", todos);
        ResponseOffset responseOffset = new ResponseOffset(currentPageNumber,
                size, totalPage, hasNext, hasPrevious, todos);

        log.info("Offset Response Offset: {}", responseOffset);
        return responseOffset;
    }

    @PostMapping("/make")
    public ResponseEntity<String> makeData(@RequestBody RequestData requestData) {
        log.info("Make Data size: {}, Request: {}", requestData.articles().size(), requestData.articles());
        boolean isSuccess = totalPagingService.makeData(requestData);

        if (isSuccess) {
            return new ResponseEntity<>("데이터 저장 성공", HttpStatus.OK);
        }
        return new ResponseEntity<>("데이터 저장 에러", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}