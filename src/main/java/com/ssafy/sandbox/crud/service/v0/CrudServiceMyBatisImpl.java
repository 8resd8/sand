package com.ssafy.sandbox.crud.service.v0;

import com.ssafy.sandbox.crud.dto.v0.RequestTodo;
import com.ssafy.sandbox.crud.dto.v0.Todo;
import com.ssafy.sandbox.crud.repository.CrudMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "myBatis")
@RequiredArgsConstructor
public class CrudServiceMyBatisImpl implements CrudService {

    private final CrudMapper crudMapper;

    @Override
    public void saveTodo(RequestTodo requestTodo) {
        crudMapper.saveTodo(requestTodo);
    }

    @Override
    public int updateToggle(Long id) {
        return crudMapper.updateToggle(id);
    }

    @Override
    public Todo findById(Long id) {
        return crudMapper.findById(id);
    }

    @Override
    public List<Todo> findAll() {
        return crudMapper.findAll();
    }

    @Override
    public int deleteTodo(Long id) {
        return crudMapper.deleteTodo(id);
    }

    @Override
    public List<Todo> cursorPaging(Long cursorId, int count) {
        return crudMapper.cursorPaging(cursorId, count);
    }

    @Override
    public List<Todo> offsetPaging(int size, int offset) {
        return crudMapper.offsetPaging(size, offset);
    }

    @Override
    public int getTotalCount() {
        return crudMapper.getTotalCount();
    }
}
