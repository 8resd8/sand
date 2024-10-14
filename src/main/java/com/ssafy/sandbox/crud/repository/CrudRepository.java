package com.ssafy.sandbox.crud.repository;

import com.ssafy.sandbox.crud.dto.ResponseTodo;
import com.ssafy.sandbox.crud.dto.RequestTodo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CrudRepository {

    private static final List<ResponseTodo> todos = new ArrayList<>();
    private static long id;

    public CrudRepository() {
    }

    public void saveTodo(RequestTodo requestTodo) {
        todos.add(new ResponseTodo(++id, requestTodo.getContent(), true));
    }

    public void updateToggle(Long id) {
        ResponseTodo findTodo = findById(id);
        if (findTodo != null) {
            int index = todos.indexOf(findTodo);
            todos.set(index, new ResponseTodo(id, findTodo.getContent(), !findTodo.isCompleted()));
        }
    }

    public ResponseTodo findById(Long id) {
        return todos.stream().
                filter(todo -> todo.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<ResponseTodo> selectAllTodo() {
        return new ArrayList<>(todos);
    }

    public void deleteTodo(Long id) {
        ResponseTodo find = findById(id);
        if (find != null) todos.remove(find);
    }
}