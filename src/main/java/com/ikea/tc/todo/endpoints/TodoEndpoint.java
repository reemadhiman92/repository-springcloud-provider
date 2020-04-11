package com.ikea.tc.todo.endpoints;

import com.ikea.tc.todo.manager.TodoManager;
import com.ikea.tc.todo.manager.TodoNotFoundException;
import com.ikea.tc.todo.model.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = TodoEndpoint.ENDPOINT)
public class TodoEndpoint {
    public static final String ENDPOINT = "/api/todos";
    private static final Logger LOGGER = LoggerFactory.getLogger(TodoEndpoint.class);

    @Autowired
    private TodoManager todoManager;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<Todo> createTodo(@RequestBody Todo dto) throws InvalidTodoException {
        if (dto.getTask() == null || dto.getTask().trim().length() == 0) {
            throw new InvalidTodoException("No task was mentioned.");
        }
        Todo todo = todoManager.createTodo(dto.getTask());
        return new ResponseEntity<>(todo, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Todo> getTodo(@PathVariable("id") long id) throws TodoNotFoundException {
        return new ResponseEntity<>(todoManager.getTodo(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteTodo(@PathVariable("id") long id) {
        todoManager.deleteTodo(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public ResponseEntity<Todo> updateTodo(@PathVariable("id") long id, @RequestBody Todo dto) throws TodoNotFoundException {
        Todo todo = todoManager.getTodo(id);
        todo.setTask(dto.getTask());
        todo.setDone(dto.isDone());
        todo = todoManager.updateTodo(todo);
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Page<Todo>> getTodos(
            @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(value = "size", defaultValue = "20", required = false) Integer size
    ) {
        Page<Todo> todoPage = todoManager.getTodos(page, size);
        List<Todo> todos = todoPage.getContent()
                .stream()
                .collect(Collectors.toList());
        Page<Todo> pages = new PageImpl<>(todos, PageRequest.of(page, size), todoPage.getTotalElements());
        return new ResponseEntity<>(pages, HttpStatus.OK);
    }
}
