package com.ssafy.sandbox.crud.repository;

import com.ssafy.sandbox.crud.dto.RequestTodo;
import com.ssafy.sandbox.crud.dto.Todo;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@MybatisTest
@Transactional
@Rollback
public class CrudMapperTest {

    @Autowired
    private CrudMapper crudMapper;

    @Test
    public void testSaveTodo() {
        // Given
        RequestTodo requestTodo = new RequestTodo("Sample content");

        // When
        crudMapper.saveTodo(requestTodo);

        // Then
        List<Todo> todos = crudMapper.findAll();
        assertThat(todos).isNotEmpty();
        assertThat(todos.get(0).content()).isEqualTo("Sample content");
    }
}
