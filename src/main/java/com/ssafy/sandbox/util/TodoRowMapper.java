package com.ssafy.sandbox.util;

import com.ssafy.sandbox.crud.dto.v0.Todo;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TodoRowMapper implements RowMapper<Todo> {
    @Override
    public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Todo(
                rs.getLong("id"),
                rs.getString("content"),
                rs.getBoolean("completed")
        );
    }

}
