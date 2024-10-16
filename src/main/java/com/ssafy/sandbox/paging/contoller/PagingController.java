package com.ssafy.sandbox.paging.contoller;

import com.ssafy.sandbox.crud.dto.ResponseTodo;
import com.ssafy.sandbox.paging.dto.RequestCursor;
import com.ssafy.sandbox.paging.dto.RequestOffset;
import com.ssafy.sandbox.paging.dto.ResponsePaging;
import com.ssafy.sandbox.paging.service.OffsetService;
import com.ssafy.sandbox.paging.service.CursorService;
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

    private final CursorService CursorService;
    private final OffsetService offsetService;

    @GetMapping("/paging")
    @ResponseStatus(HttpStatus.OK)
    public ResponsePaging cursorPaging(@ModelAttribute RequestCursor requestCursor) {
        log.info("cursorPaging: {}", requestCursor);

        List<ResponseTodo> pageTotos = CursorService.pagedTotos(requestCursor.size(), requestCursor.cursorId());
        boolean hasNext = CursorService.hasNext(requestCursor.size(), requestCursor.cursorId());

//        ResponsePaging responsePaging = new ResponsePaging("정상적으로 요청되었습니다.",
//                OffsetService, requestCursor.size(), hasNext, pageTotos);
//        log.info("cursorPaging: {}", responsePaging);

        return null;
    }

    @GetMapping("/cursor")
    @ResponseStatus(HttpStatus.OK)
    public ResponsePaging offsetPaging(@ModelAttribute RequestOffset requestOffset) {
        log.info("offsetPaging: {}", requestOffset);

        List<ResponseTodo> pageTotos = CursorService.pagedTotos(requestOffset.size(), requestOffset.page());
        boolean hasNext = CursorService.hasNext(requestOffset.size(), requestOffset.page());

        ResponsePaging responsePaging = new ResponsePaging("정상적으로 요청되었습니다.", requestOffset.page(), requestOffset.size(), hasNext, pageTotos);
        log.info("responsePaging: {}", responsePaging);

        return responsePaging;
    }


}