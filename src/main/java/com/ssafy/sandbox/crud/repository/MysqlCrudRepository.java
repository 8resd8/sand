package com.ssafy.sandbox.crud.repository;

import com.ssafy.sandbox.crud.dto.RequestTodo;
import com.ssafy.sandbox.crud.dto.ResponseTodo;
import com.ssafy.sandbox.util.ResponseTodoRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Primary
@Repository
@Slf4j
@RequiredArgsConstructor
public class MysqlCrudRepository implements CrudRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void saveTodo(RequestTodo requestTodo) {
        String sql = "insert into todos (content, completed) values (?, ?)";
        int update = jdbcTemplate.update(sql, requestTodo.getContent(), false);
        log.info("mySql saveTodo: {}, 결과: {}", requestTodo.getContent(), update);
    }

    @Override
    public int updateToggle(Long id) {
        log.info("mySql updateToggle: {}", id);
        String sql = "update todos set completed = !completed where id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public ResponseTodo findById(Long id) {
        log.info("Mysql, findById: {}", id);
        String sql = "select * from todos where id = ?";
        return jdbcTemplate.queryForObject(sql, new ResponseTodoRowMapper(), id);
    }

    @Override
    public List<ResponseTodo> findAll() {
        String sql = "select id, content, completed from todos";
        List<ResponseTodo> query = jdbcTemplate.query(sql, new ResponseTodoRowMapper());
        log.info("Mysql, findAll: {}", query);
        return query;
    }

    @Override
    public int deleteTodo(Long id) {
        log.info("Mysql, deleteTodo: {}", id);
        String sql = "delete from todos where id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
