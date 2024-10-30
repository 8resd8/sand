package com.ssafy.sandbox.curd.repository;

import com.ssafy.sandbox.crud.dto.RequestTodo;
import com.ssafy.sandbox.crud.dto.Todo;
import com.ssafy.sandbox.crud.repository.CrudMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
@Transactional
public class CrudMapperTest {

    @Autowired
    private CrudMapper crudMapper;

    @BeforeEach
    void setUp() {
        crudMapper.saveTodo(new RequestTodo("Test1"));
        crudMapper.saveTodo(new RequestTodo("Test2"));
    }


    @Test
    void findAll() {
        List<Todo> todos = crudMapper.findAll();
        assertEquals(2, todos.size());
    }

    @Test
    void findById() {
        Todo todo1 = firstTodo();
        Long id1 = todo1.id();
        String content1 = todo1.content();

        Todo todo2 = crudMapper.findById(id1);
        String content2 = todo2.content();
        Long id2 = todo2.id();

        assertEquals(content1, content2);
        assertEquals(id1, id2);
    }

    /**
     * 잘못된 테스트 코드
     * Java 객체의 상태와 DB 상태가 독립적이다.
     * crudMapper.findAll() 으로 얻은 객체는 메모리에 로드된 객체, DB와 동기화되지 않음
     * DB의 상태를 crudMapper.updateToggle(todo.id()); 로 변경했지만 현재 코드는
     * 기존 메모리에 로드된 boolean상태를 바꾸지 않은 것이다.
     * 따라서 상태를 바꿨으면 다시 DB에서 조회한 후 값을 비교해야 한다.
     *
     * @Test void updateToggle() {
     * Todo todo = crudMapper.findAll().get(0);
     * boolean one = todo.completed();
     * <p>
     * crudMapper.updateToggle(todo.id());
     * <p>
     * boolean two = todo.completed();
     * <p>
     * assertNotEquals(one, two);
     * }
     */

    // DB에서 다시 값을 조회한 후 비교해야 옳게 된 것
    @Test
    void updateToggle() {
        Todo todo1 = firstTodo();
        boolean one = todo1.completed();
        crudMapper.updateToggle(todo1.id());

        Todo todo2 = crudMapper.findById(todo1.id());
        boolean two = todo2.completed();

        assertNotEquals(one, two);
    }


    @Test
    void delete() {
        Todo todo = firstTodo();
        Long id = todo.id();

        int deleteCount = crudMapper.deleteTodo(id);

        assertEquals(1, deleteCount);
        assertNull(crudMapper.findById(id));
    }

    @Test
    void cursorPaging() {
        Long cursorId = firstTodo().id();

        List<Todo> todos = crudMapper.cursorPaging(cursorId, 1);

        // 1개만 가져왔는지 확인
        assertEquals(1, todos.size());

        // where 테스트, 현재 id가 cursor보다 커야한다
        assertTrue(todos.get(0).id() > cursorId);
    }

    @Test
    void offsetPaging() {
        // limit 개수 OFFSET 시작위치
        List<Todo> todos = crudMapper.offsetPaging(2, 0);

        assertEquals(2, todos.size());
    }

    @Test
    void getTotalCount() {
        int actualCount = crudMapper.getTotalCount();

        assertEquals(2, actualCount);
    }

    private Todo firstTodo() {
        return crudMapper.findAll().get(0);
    }

}