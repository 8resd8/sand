package com.ssafy.sandbox.paging.repository;

import com.ssafy.sandbox.crud.dto.ResponseTodo;
import com.ssafy.sandbox.paging.dto.ResponseDto;
import com.ssafy.sandbox.util.ResponseTodoRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PagingRepository {

    private final JdbcTemplate jdbcTemplate;

    public PagingRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // start ~ end까지 잘라서 가져오기: select

    public List<ResponseTodo> findSubset(int start, int count) {
        String sql = "select * from todos limit ?, ?"; // 0부터 시작
        List<ResponseTodo> query = jdbcTemplate.query(sql, new ResponseTodoRowMapper(), start, count);
        return query;
    }

}