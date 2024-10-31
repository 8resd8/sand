package com.ssafy.sandbox.paging.contoller;

import com.ssafy.sandbox.paging.dto.*;
import com.ssafy.sandbox.paging.service.TotalPagingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
public class PagingController {

    private final TotalPagingService totalPagingService;


    @GetMapping("/paging/cursor")
    @ResponseStatus(HttpStatus.OK)
    public ResponseCursor cursorPaging(@ModelAttribute RequestCursor requestCursor) {

        return totalPagingService.responseCursor(requestCursor);
    }


    @GetMapping("/paging/offset")
    @ResponseStatus(HttpStatus.OK)
    public ResponseOffset offsetPaging(@ModelAttribute RequestOffset requestOffset) {

        return totalPagingService.responseOffset(requestOffset);
    }

    @PostMapping("/make")
    public ResponseEntity<String> makeData(@RequestBody RequestData requestData) {
        boolean isSuccess = totalPagingService.makeData(requestData);

        if (isSuccess) {
            return new ResponseEntity<>("데이터 저장 성공", HttpStatus.OK);
        }
        return new ResponseEntity<>("데이터 저장 에러", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}