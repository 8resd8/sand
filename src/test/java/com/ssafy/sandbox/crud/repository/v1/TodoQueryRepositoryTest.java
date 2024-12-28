package com.ssafy.sandbox.crud.repository.v1;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.sandbox.crud.dto.TodoResponse;
import com.ssafy.sandbox.crud.entity.Todo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.ssafy.sandbox.crud.entity.QTodo.todo;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class TodoQueryRepositoryTest {


    @Autowired
    TodoQueryRepository todoQueryRepository;

    @Autowired
    TodoRepository todoRepository;

    @BeforeEach
    void setUp() {
        for (int i = 1; i <= 10; i++) {
            Todo todo = new Todo("할일: " + i);
            todoRepository.save(todo);
        }
    }

    @Test
    void findAllTodo() {
        List<TodoResponse> findTodos = todoQueryRepository.findAllTodo();

        assertThat(findTodos.size()).isEqualTo(10);
    }

    @Test
    void updateToggle() {
        Optional<Todo> findTodo = todoRepository.findById(1L);
        assertThat(findTodo).isPresent();
        Todo todo = findTodo.get();

        assertThat(todo.isCompleted()).isFalse();

        todo.updateToggle();

        assertThat(todo.isCompleted()).isTrue();

    }
}