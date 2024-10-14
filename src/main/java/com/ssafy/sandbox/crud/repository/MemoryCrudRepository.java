package com.ssafy.sandbox.crud.repository;

import com.ssafy.sandbox.crud.dto.ResponseTodo;
import com.ssafy.sandbox.crud.dto.RequestTodo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MemoryCrudRepository implements CrudRepository {

    private static final List<ResponseTodo> todos = new ArrayList<>();
    private static long sequence = 0L;

    public MemoryCrudRepository() {
    }

    public void saveTodo(RequestTodo requestTodo) {
        todos.add(new ResponseTodo(++sequence, requestTodo.getContent(), true));
    }

    public int updateToggle(Long id) {
        ResponseTodo findTodo = findById(id);
        if (findTodo != null) {
            int index = todos.indexOf(findTodo);
            todos.set(index, new ResponseTodo(id, findTodo.getContent(), !findTodo.isCompleted()));
            return 1;
        }
        return 0;
    }

    public ResponseTodo findById(Long id) {
        return todos.stream().
                filter(todo -> todo.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<ResponseTodo> findAll() {
        return new ArrayList<>(todos);
    }

    public int deleteTodo(Long id) {
        ResponseTodo find = findById(id);
        if (find != null) {
            todos.remove(find);
            return 1;
        }
        return 0;
    }
}