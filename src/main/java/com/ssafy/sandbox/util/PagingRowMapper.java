package com.ssafy.sandbox.util;

import com.ssafy.sandbox.paging.dto.Paging;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PagingRowMapper implements RowMapper<Paging> {
    @Override
    public Paging mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Paging(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getTimestamp("createdAt")
        );
    }
}