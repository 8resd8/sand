package com.ssafy.sandbox.paging.service;

import com.ssafy.sandbox.paging.dto.Paging;
import com.ssafy.sandbox.paging.repository.MysqlPagingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TotalPagingService {

    private final MysqlPagingRepository mysqlPagingRepository;

    public List<Paging> pagedTotos(int size, Long cursorId) {
        // 첫 요청이면 그대로 size 만큼 돌려주기
        if (cursorId == 0) {
            return mysqlPagingRepository.cursorPaging(0L, size);
        }

        List<Paging> pages = mysqlPagingRepository.cursorPaging(cursorId, size + 1);
        if (pages.size() > size) pages.remove(pages.size() - 1);

        return pages;
    }

    public boolean hasNext(Long cursorId, int size) {
        return mysqlPagingRepository.cursorPaging(cursorId, size + 1).size() > size;
    }

    public Long getNextCursor(List<Paging> pages) {
        if (pages.isEmpty()) return 0L;

        // 마지막 데이터 ID 반환, 다음 페이지 커서로 사용
        return pages.get(pages.size() - 1).id();
    }

    // 위는 Cursor, 아래는 Offset

    public List<Paging> pagedTodos(int size, int page) {
        int offset = (page - 1) * size; // limit 개수 OFFSET 시작지점
        return mysqlPagingRepository.offsetPaging(size, offset);
    }

    public int currentPageNumber(int page) {
        return page; // 1부터 시작
    }

    public boolean hasPrevious(int page) {
        return page > 0; // 이전 페이지
    }

    public boolean hasNext(int size, int page) {
        return (page - 1) < totalPage(size); // 다음 페이지
    }

    public int totalPage(int size) {
        int totalData = mysqlPagingRepository.getTotalCount();  // 총 데이터 개수
        return (int) Math.ceil((double) totalData / size);  // 총 페이지 수 계산, 올림
    }
}
