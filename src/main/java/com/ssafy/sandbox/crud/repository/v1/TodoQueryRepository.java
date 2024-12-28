package com.ssafy.sandbox.crud.repository.v1;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.sandbox.crud.dto.TodoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ssafy.sandbox.crud.entity.QTodo.todo;

@Repository
@RequiredArgsConstructor
public class TodoQueryRepository {

    private final JPAQueryFactory queryFactory;


    public List<TodoResponse> findAllTodo() {
        return queryFactory
                .select(Projections.constructor(TodoResponse.class,
                        todo.id,
                        todo.content,
                        todo.completed))
                .from(todo)
                .fetch();
    }
}
