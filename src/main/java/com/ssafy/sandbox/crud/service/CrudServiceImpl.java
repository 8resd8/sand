package com.ssafy.sandbox.crud.service;

import com.ssafy.sandbox.crud.dto.RequestTodo;
import com.ssafy.sandbox.crud.dto.ResponseTodo;
import com.ssafy.sandbox.crud.dto.Todo;
import com.ssafy.sandbox.crud.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@RequiredArgsConstructor
public class CrudServiceImpl implements CrudService{


    private CrudRepository crudRepository;

    public CrudServiceImpl(CrudRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

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
