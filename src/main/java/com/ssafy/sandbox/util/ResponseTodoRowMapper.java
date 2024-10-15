package com.ssafy.sandbox.util;

import com.ssafy.sandbox.crud.dto.ResponseTodo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResponseTodoRowMapper implements RowMapper<ResponseTodo> {
    @Override
    public ResponseTodo mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ResponseTodo(
                rs.getLong("id"),
                rs.getString("content"),
                rs.getBoolean("completed")
        );
    }

}
