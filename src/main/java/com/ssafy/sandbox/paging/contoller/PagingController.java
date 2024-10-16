package com.ssafy.sandbox.paging.contoller;

import com.ssafy.sandbox.crud.dto.ResponseTodo;
import com.ssafy.sandbox.paging.dto.RequestOffset;
import com.ssafy.sandbox.paging.dto.ResponsePaging;
import com.ssafy.sandbox.paging.service.OffsetService;
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


    @GetMapping("/offset")
    @ResponseStatus(HttpStatus.OK)
    public ResponsePaging offsetPaging(@ModelAttribute RequestOffset requestOffset) {
        log.info("offsetPaging: {}", requestOffset);

        List<ResponseTodo> pageTotos = offsetService.pagedTotos(requestOffset.size(), requestOffset.page());
        boolean hasNext = offsetService.hasNext(requestOffset.size(), requestOffset.page());

        ResponsePaging responsePaging = new ResponsePaging("정상적으로 요청되었습니다.", requestOffset.page(), requestOffset.size(), hasNext, pageTotos);
        log.info("responsePaging: {}", responsePaging);

        return responsePaging;
    }
}