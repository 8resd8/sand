package com.ssafy.sandbox.crud.repository;

import com.ssafy.sandbox.crud.dto.TodosResponse;
import com.ssafy.sandbox.crud.entity.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
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
        TodosResponse findTodos = todoQueryRepository.findAllTodo();

        assertThat(findTodos.getTodos().size()).isEqualTo(10);
    }

    @Test
    void updateToggle() {
        Todo todo1 = todoRepository.save(new Todo("업데이트 테스트"));
        Optional<Todo> findTodo = todoRepository.findById(todo1.getId());
        assertThat(findTodo).isPresent();
        Todo todo = findTodo.get();

        assertThat(todo.isCompleted()).isFalse();

        todo.updateToggle();

        assertThat(todo.isCompleted()).isTrue();
    }
}