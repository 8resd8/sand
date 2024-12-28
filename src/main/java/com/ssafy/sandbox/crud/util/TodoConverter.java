package com.ssafy.sandbox.crud.util;

import com.ssafy.sandbox.crud.dto.TodoRequest;
import com.ssafy.sandbox.crud.entity.Todo;

public class TodoConverter {

    public static Todo toEntity(TodoRequest request) {
        return new Todo(request.getContent());
    }
}
