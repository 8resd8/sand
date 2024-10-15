package com.ssafy.sandbox.paging.contoller;

import com.ssafy.sandbox.paging.dto.RequestPaging;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
public class PagingController {

    @GetMapping("/offset")
    public ResponseEntity<HashMap<String, Object>> offsetPaging(@ModelAttribute RequestPaging requestPaging) {
        HashMap<String, Object> response = new HashMap<>();
        log.info("offsetPaging: {}", requestPaging);

//        List<ResponsePaging> todos = new ArrayList<>();
        response.put("massage", "정상적으로 요청되었습니다.");

        return ResponseEntity.ok(response);
    }
}