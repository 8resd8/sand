package com.ssafy.sandbox.paging.repository;

import com.ssafy.sandbox.paging.dto.Paging;
import com.ssafy.sandbox.util.PagingRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcPagingRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<Paging> cursorPaging(Long cursorId, int count) {
        String sql = "select * from paging where id > ? limit ?"; // 0부터 시작
        return jdbcTemplate.query(sql, new PagingRowMapper(), cursorId, count);
    }

    public List<Paging> offsetPaging(int size, int offset) {
        String sql = "SELECT * FROM paging limit ? OFFSET ?";
        return jdbcTemplate.query(sql, new PagingRowMapper(), size, offset);
    }

    public int getTotalCount() {
        String sql = "select count(*) from paging";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}