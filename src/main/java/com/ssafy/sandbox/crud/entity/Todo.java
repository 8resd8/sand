package com.ssafy.sandbox.crud.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "todos")
public class Todo {

    @Id @GeneratedValue
    @Column(name = "todo_id")
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "completed")
    @ColumnDefault("false")
    private boolean completed;

    public Todo(String content) {
        this.content = content;
    }

    public void updateToggle() {
        this.completed = !this.completed;
    }
}
