package com.ssafy.sandbox.paging.contoller;

import com.ssafy.sandbox.crud.dto.ResponseTodo;
import com.ssafy.sandbox.crud.service.CrudService;
import com.ssafy.sandbox.paging.dto.RequestPaging;
import com.ssafy.sandbox.paging.dto.ResponsePaging;
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
    private final CrudService crudService;
    private final PagingService pagingService;

    @GetMapping("/offset")
    @ResponseStatus(HttpStatus.OK)
    public ResponsePaging offsetPaging(@ModelAttribute RequestPaging requestPaging) {
        log.info("offsetPaging: {}", requestPaging);

        List<ResponseTodo> pageTotos = pagingService.pagedTotos(requestPaging.size(), requestPaging.page());
        boolean hasNext = pagingService.hasNext(requestPaging.size(), requestPaging.page());
        log.info("pageTodo: {}", pageTotos);

        ResponsePaging responsePaging = new ResponsePaging("정상적으로 요청되었습니다.", requestPaging.page(), requestPaging.size(), hasNext, pageTotos);

        return responsePaging;
    }
}