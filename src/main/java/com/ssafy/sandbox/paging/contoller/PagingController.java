package com.ssafy.sandbox.paging.contoller;

import com.ssafy.sandbox.paging.dto.CursorResponse;
import com.ssafy.sandbox.paging.dto.OffsetResponse;
import com.ssafy.sandbox.paging.dto.PageRequest;
import com.ssafy.sandbox.paging.service.PageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
public class PagingController {

    private final PageService pageService;


    @GetMapping("/paging/offset")
    public OffsetResponse offsetPaging(Pageable pageable) {
        return pageService.getOffset(pageable);
    }

    @GetMapping("/paging/cursor")
    public CursorResponse cursorPaging(@RequestParam(name = "cursorId", required = false, defaultValue = "0") Long cursorId,
                                       @RequestParam(name = "size", defaultValue = "10") int size) {
        return pageService.getCursor(cursorId, size);
    }


    @PostMapping("/make")
    public void saveAll(@RequestBody PageRequest request) {
        pageService.saveAll(request);
    }
}