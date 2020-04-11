package com.ikea.tc.todo.functional;

import com.ikea.tc.todo.manager.TodoManager;
import com.ikea.tc.todo.manager.TodoNotFoundException;
import com.ikea.tc.todo.model.Todo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class TodoManagerIntegrationTest extends AbstractIntegrationTestSupport {

    @Autowired
    private TodoManager todoManager;
    @Autowired
    private Environment env;
    private Todo todo;

    @Before
    public void before() {
        todo = todoManager.createTodo("Fix build");
    }

    @After
    public void after() {
        if (todo != null) {
            todoManager.deleteTodo(todo.getId());
        }
    }

    @Test
    public void createTodo() {
        assertThat(todo).isNotNull();
        assertThat(todo.getId()).isNotNull();
    }

    @Test
    public void getTodo() throws TodoNotFoundException {
        Todo foundTodo = todoManager.getTodo(this.todo.getId());
        assertThat(foundTodo).isNotNull();
        assertThat(foundTodo).isEqualTo(todo);
        assertThat(foundTodo.getId()).isEqualTo(todo.getId());
    }

    @Test
    public void findTodoByTask() {
        List<Todo> todoByTask = todoManager.findTodoByTask(todo.getTask());
        assertThat(todoByTask).isNotNull();
        assertThat(todoByTask.size()).isEqualTo(1);
        assertThat(todoByTask.get(0)).isEqualTo(todo);
    }

    @Test
    public void deleteTodo() {
        long todoCount = todoManager.getTodoCount();
        todoManager.deleteTodo(todo.getId());
        assertThat(todoManager.getTodoCount()).isEqualTo(todoCount - 1);
        todo = null; //so that we don't try to delete again on "after"
    }

    @Test
    public void getTodos() {
        Todo todo1 = todoManager.createTodo("Test1");
        Todo todo2 = todoManager.createTodo("Test2");
        Page<Todo> todos = todoManager.getTodos(0, 20);
        assertThat(todos).isNotNull();
        assertThat(todos.getContent().contains(todo1)).isTrue();
        assertThat(todos.getContent().contains(todo2)).isTrue();

        todoManager.deleteTodo(todo1.getId());
        todoManager.deleteTodo(todo2.getId());
    }

    @Test
    public void deleteAllTodo() {
        todoManager.deleteAll();
        assertThat(todoManager.getTodoCount()).isEqualTo(0l);
        todo = null; //so that we don't try to delete again on "after"
    }

    @Test
    public void getTodoCount() {
        long todoCount = todoManager.getTodoCount();
        assertThat(todoCount).isEqualTo(1);
    }
}
