package com.ssafy.sandbox.crud.dao;

import com.ssafy.sandbox.crud.dto.ResponseTodo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TodoDaoImpl implements  TodoDao{
    @Override
    public ResponseTodo createTodo() {
        return null;
    }

    @Override
    public int insertTodo(ResponseTodo responseTodo) {
        return 0;
    }

    @Override
    public ResponseTodo selectTodo(Long id) {
        return null;
    }

    @Override
    public List<ResponseTodo> selectAll() {
        return List.of();
    }

    @Override
    public int deleteById(Long id) {
        return 0;
    }

    @Override
    public int updateToggle(Long id, boolean completed) {
        return 0;
    }
}
