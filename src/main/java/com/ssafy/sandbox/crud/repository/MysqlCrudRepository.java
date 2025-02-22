package com.ssafy.sandbox.crud.repository;

import com.ssafy.sandbox.crud.dto.v0.Todo;
import com.ssafy.sandbox.crud.dto.v0.RequestTodo;
import com.ssafy.sandbox.util.TodoRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Slf4j
@RequiredArgsConstructor
@Primary
public class MysqlCrudRepository implements CrudRepository {

    private final JdbcTemplate jdbcTemplate;
    private final TodoRowMapper todoRowMapper;

    @Override
    public void saveTodo(RequestTodo requestTodo) {
        String sql = "insert into todos (content, completed) values (?, ?)";
        jdbcTemplate.update(sql, requestTodo.getContent(), false);
    }

    @Override
    public int updateToggle(Long id) {
        String sql = "update todos set completed = !completed where id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public Todo findById(Long id) {
        String sql = "select * from todos where id = ?";
        return jdbcTemplate.queryForObject(sql, todoRowMapper, id);
    }

    @Override
    public List<Todo> findAll() {
        String sql = "select id, content, completed from todos";
        return jdbcTemplate.query(sql, todoRowMapper);
    }

    @Override
    public int deleteTodo(Long id) {
        String sql = "delete from todos where id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Todo> cursorPaging(Long cursorId, int count) {
        String sql = "select * from todos where id > ? limit ?"; // 0부터 시작
        return jdbcTemplate.query(sql, todoRowMapper, cursorId, count);
    }

    @Override
    public List<Todo> offsetPaging(int size, int offset) {
        String sql = "SELECT * FROM todos limit ? OFFSET ?";
        return jdbcTemplate.query(sql, todoRowMapper, size, offset);
    }

    @Override
    public int getTotalCount() {
        String sql = "select count(*) from todos";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}