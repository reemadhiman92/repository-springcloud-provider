package com.ikea.tc.todo.system;

import com.ikea.tc.todo.endpoints.TodoEndpoint;
import com.ikea.tc.todo.model.Todo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.assertj.core.api.Java6Assertions.assertThat;


public class TodoEndpointTest extends AbstractEndpointTestSupport {

    private Todo todo;

    @Before
    public void before() {
        todo = template.postForEntity(TodoEndpoint.ENDPOINT, new Todo("Start the build"), Todo.class).getBody();
    }

    @After
    public void after() {
        if (todo != null) {
            template.delete(TodoEndpoint.ENDPOINT + "/" + todo.getId());
        }
    }

    @Test
    public void createTodo() {
        Todo releaseTodo = new Todo("Release version 1.2");
        Todo returnedTodo = template.postForEntity(TodoEndpoint.ENDPOINT, releaseTodo, Todo.class).getBody();
        assertThat(returnedTodo).isNotNull();
        assertThat(returnedTodo.getId()).isNotNull();
        assertThat(releaseTodo.getTask()).isEqualTo(returnedTodo.getTask());

        template.delete(TodoEndpoint.ENDPOINT + "/" + returnedTodo.getId());
    }

    @Test
    public void getTodo() {
        ResponseEntity<Todo> response = template.getForEntity(TodoEndpoint.ENDPOINT + "/" + todo.getId(), Todo.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        Todo responseTodo = response.getBody();
        assertThat(responseTodo).isNotNull();
        assertThat(responseTodo.getId()).isEqualTo(todo.getId());
        assertThat(responseTodo.getTask()).isEqualTo(todo.getTask());
    }

    @Test
    public void deleteTodo() {
        template.delete(TodoEndpoint.ENDPOINT + "/" + todo.getId());

        ResponseEntity<Todo> response = template.getForEntity(TodoEndpoint.ENDPOINT + "/" + todo.getId(), Todo.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

        todo = null;
    }

    @Test
    public void getTodos() {
        ResponseEntity<Map> response = template.getForEntity(TodoEndpoint.ENDPOINT, Map.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        Map todos = response.getBody();
        assertThat(todos).isNotNull();
        assertThat(todos.size()).isNotNull();
        assertThat(todos.size()).isGreaterThan(0);
    }
}
