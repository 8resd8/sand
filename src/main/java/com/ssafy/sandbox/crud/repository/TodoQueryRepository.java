package com.ssafy.sandbox.crud.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.sandbox.crud.dto.TodoResponse;
import com.ssafy.sandbox.crud.dto.TodosResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ssafy.sandbox.crud.entity.QTodo.todo;

@Repository
@RequiredArgsConstructor
public class TodoQueryRepository {

    private final JPAQueryFactory queryFactory;


    public List<TodoResponse> findTodo() {
        return queryFactory
                .select(Projections.constructor(TodoResponse.class,
                        todo.id,
                        todo.content,
                        todo.completed))
                .from(todo)
                .fetch();
    }

    public TodosResponse findAllTodo() {
        return new TodosResponse(findTodo());
    }
}
