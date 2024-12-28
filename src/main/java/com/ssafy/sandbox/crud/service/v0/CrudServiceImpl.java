package com.ssafy.sandbox.crud.service.v0;

import com.ssafy.sandbox.crud.dto.v0.RequestTodo;
import com.ssafy.sandbox.crud.dto.v0.Todo;
import com.ssafy.sandbox.crud.repository.CrudRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CrudServiceImpl implements CrudService {

    private final CrudRepository crudRepository;

    @Override
    public void saveTodo(RequestTodo requestTodo) {
        crudRepository.saveTodo(requestTodo);
    }

    @Override
    public int updateToggle(Long id) {
        return crudRepository.updateToggle(id);
    }

    @Override
    public Todo findById(Long id) {
        return crudRepository.findById(id);
    }

    @Override
    public List<Todo> findAll() {
        return crudRepository.findAll();
    }

    @Override
    public int deleteTodo(Long id) {
        return crudRepository.deleteTodo(id);
    }

    public List<Todo> cursorPaging(Long cursorId, int count) {
        return crudRepository.cursorPaging(cursorId, count);
    }

    public List<Todo> offsetPaging(int size, int page) {
        return crudRepository.offsetPaging(size, page);
    }

    @Override
    public int getTotalCount() {
        return crudRepository.getTotalCount();
    }
}
