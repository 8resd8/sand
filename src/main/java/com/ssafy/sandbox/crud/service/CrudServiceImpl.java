package com.ssafy.sandbox.crud.service;

import com.ssafy.sandbox.crud.dto.RequestTodo;
import com.ssafy.sandbox.crud.dto.ResponseTodo;
import com.ssafy.sandbox.crud.repository.CrudRepository;
import com.ssafy.sandbox.util.ResponseTodoRowMapper;
import lombok.RequiredArgsConstructor;
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

    public List<ResponseTodo> findSubset(Long cursorId, int count) {
        return crudRepository.findSubset(cursorId, count);
    }
}
