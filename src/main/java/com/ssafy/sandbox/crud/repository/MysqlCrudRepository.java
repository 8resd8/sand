package com.ssafy.sandbox.crud.repository;

import com.ssafy.sandbox.crud.dto.RequestTodo;
import com.ssafy.sandbox.crud.dto.ResponseTodo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Primary
@Repository
@Slf4j
@RequiredArgsConstructor
public class MysqlCrudRepository implements CrudRepository{

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void saveTodo(RequestTodo requestTodo) {
        String sql = "insert into todos (content, completed) values (?, ?)";
        int update = jdbcTemplate.update(sql, requestTodo.getContent(), false);
        log.info("saveRepository: {}, 결과: {}", requestTodo.getContent(), update);
    }

    @Override
    public int updateToggle(Long id) {
        log.info("updateToggle: {}", id);
        String sql = "update todos set completed = !completed where id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public ResponseTodo findById(Long id) {
        String sql = "select * from todos where id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(ResponseTodo.class), id);
    }

    @Override
    public List<ResponseTodo> findAll() {
        String sql = "select id, content, completed from todos";
        List<ResponseTodo> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ResponseTodo.class));
        log.info("findAllRepository: {}", query);
        return query;
    }

    @Override
    public int deleteTodo(Long id) {
        String sql = "delete from todos where id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
