package com.ssafy.sandbox.paging.service.v0;

import com.ssafy.sandbox.paging.dto.v0.Paging;
import com.ssafy.sandbox.paging.repository.v0.JdbcPagingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CursorService  {

    private final JdbcPagingRepository jdbcPagingRepository;

    public CursorService(JdbcPagingRepository jdbcPagingRepository) {
        this.jdbcPagingRepository = jdbcPagingRepository;
    }

    public List<Paging> pagedTotos(int size, Long cursorId) {
        // 첫 요청이면 그대로 size 만큼 돌려주기
        if (cursorId == 0) {
            return jdbcPagingRepository.cursorPaging(0L, size);
        }

        List<Paging> pages = jdbcPagingRepository.cursorPaging(cursorId, size + 1);
        if (pages.size() > size) pages.remove(pages.size() - 1);

        log.info("cursorId: {}, pagesSize: {}", cursorId, pages.size());
        return pages;
    }

    public boolean hasNext(Long cursorId, int size) {
        return jdbcPagingRepository.cursorPaging(cursorId, size + 1).size() > size;
    }

    public Long getNextCursor(List<Paging> pages) {
        if (pages.isEmpty()) return 0L;

        // 마지막 데이터 ID 반환, 다음 페이지 커서로 사용
        log.info("lastCursor: {}", pages.get(pages.size() - 1).id());
        return pages.get(pages.size() - 1).id();
    }
}