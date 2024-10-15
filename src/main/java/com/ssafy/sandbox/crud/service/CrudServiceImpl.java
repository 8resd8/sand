package com.ssafy.sandbox.crud.service;

import com.ssafy.sandbox.crud.dto.RequestTodo;
import com.ssafy.sandbox.crud.dto.ResponseTodo;
import com.ssafy.sandbox.crud.repository.CrudRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CrudServiceImpl implements CrudService{

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
    public ResponseTodo findById(Long id) {
        return crudRepository.findById(id);
    }

    @Override
    public List<ResponseTodo> findAll() {
        return crudRepository.findAll();
    }

    @Override
    public int deleteTodo(Long id) {
        return crudRepository.deleteTodo(id);
    }
}
