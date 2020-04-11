package com.ikea.tc.todo.manager;

import com.ikea.tc.todo.model.Todo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TodoManager {
    Todo createTodo(String task);

    Todo getTodo(Long id) throws TodoNotFoundException;

    List<Todo> findTodoByTask(String task);

    void deleteTodo(Long id);

    Todo updateTodo(Todo todo) throws TodoNotFoundException;

    Page<Todo> getTodos(Integer page, Integer size);

    void deleteAll();

    long getTodoCount();
}
