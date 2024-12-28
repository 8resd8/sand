package com.ssafy.sandbox.crud.repository.v1;

import com.ssafy.sandbox.crud.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
