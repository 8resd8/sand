package com.ssafy.sandbox.paging.service.v0;

import com.ssafy.sandbox.paging.dto.v0.*;
import com.ssafy.sandbox.paging.repository.v0.MyBatisPagingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TotalPagingService {

    private final MyBatisPagingMapper myBatisPagingMapper;

    public List<Paging> getCursorPaging(int size, Long cursorId) {
        // 첫 요청이면 그대로 size 만큼 돌려주기
        if (cursorId == 0) {
            return myBatisPagingMapper.cursorPaging(0L, size);
        }

        List<Paging> pages = myBatisPagingMapper.cursorPaging(cursorId, size + 1);
        if (pages.size() > size) pages.remove(pages.size() - 1);

        return pages;
    }

    public boolean hasNext(Long cursorId, int size) {
        return myBatisPagingMapper.cursorPaging(cursorId, size + 1).size() > size;
    }

    public Long getNextCursor(List<Paging> pages) {
        if (pages.isEmpty()) return 0L;

        // 마지막 데이터 ID 반환, 다음 페이지 커서로 사용
        return pages.get(pages.size() - 1).id();
    }

    // 위는 Cursor, 아래는 Offset

    public List<Paging> getOffsetPaging(int size, int page) {
        int offset = (page - 1) * size; // limit size OFFSET offset, page: 1부터 입력
        return myBatisPagingMapper.offsetPaging(size, offset);
    }

    public int currentPageNumber(int page) {
        return page; // 1부터 시작
    }

    public boolean hasPrevious(int page) {
        return page > 1; // 이전 페이지
    }

    public boolean hasNext(int size, int page) {
        return (page - 1) < totalPage(size); // 다음 페이지
    }

    public int totalPage(int size) {
        int totalData = myBatisPagingMapper.getTotalCount();  // 총 데이터 개수
        return (int) Math.ceil((double) totalData / size);  // 총 페이지 수 계산, 올림
    }

    public boolean makeData(RequestData requestData) {
        return myBatisPagingMapper.insertPagingData(requestData.articles());
    }


    public ResponseCursor responseCursor(RequestCursor requestCursor) {
        List<Paging> pages = getCursorPaging(requestCursor.size(), requestCursor.cursorId());
        Long lastId = getNextCursor(pages);
        boolean hasNext = hasNext(requestCursor.cursorId(), requestCursor.size());

        return new ResponseCursor(lastId, requestCursor.size(), hasNext, pages);
    }


    public ResponseOffset responseOffset(RequestOffset requestOffset) {
        int page = requestOffset.page();
        int size = requestOffset.size();

        int currentPageNumber = currentPageNumber(page);
        int totalPage = totalPage(size);
        boolean hasNext = hasNext(size, page);
        boolean hasPrevious = hasPrevious(page);
        List<Paging> todos = getOffsetPaging(size, page);

        return new ResponseOffset(currentPageNumber,
                size, totalPage, hasNext, hasPrevious, todos);
    }
}
