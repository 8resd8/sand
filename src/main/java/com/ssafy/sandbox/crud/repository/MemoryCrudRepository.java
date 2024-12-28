package com.ssafy.sandbox.crud.repository;

import com.ssafy.sandbox.crud.dto.v0.Todo;
import com.ssafy.sandbox.crud.dto.v0.RequestTodo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MemoryCrudRepository implements CrudRepository {

    private static final List<Todo> todos = new ArrayList<>();
    private static long sequence = 0L;

    public MemoryCrudRepository() {
    }

    public void saveTodo(RequestTodo requestTodo) {
        todos.add(new Todo(++sequence, requestTodo.getContent(), false));
    }

    public int updateToggle(Long id) {
        Todo findTodo = findById(id);
        if (findTodo == null) return 0;

        int index = todos.indexOf(findTodo);
        todos.set(index, new Todo(id, findTodo.content(), !findTodo.completed()));
        return index;
    }

    public Todo findById(Long id) {
        return todos.stream().
                filter(todo -> todo.id().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Todo> findAll() {
        return new ArrayList<>(todos);
    }

    public int deleteTodo(Long id) {
        Todo find = findById(id);
        if (find == null) return 0;

        todos.remove(find);
        return 0;
    }

    @Override
    public List<Todo> cursorPaging(Long start, int count) {
        return List.of(); // 구현 x
    }

    @Override
    public List<Todo> offsetPaging(int size, int offset) {
        return List.of(); // 구현 X
    }

    @Override
    public int getTotalCount() {
        return 0;
    }
}