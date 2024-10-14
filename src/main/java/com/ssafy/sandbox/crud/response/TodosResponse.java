package com.ssafy.sandbox.crud.response;

import com.ssafy.sandbox.crud.dto.Todos;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TodosResponse {
    private String message;
    private List<Todos> todos;
}
