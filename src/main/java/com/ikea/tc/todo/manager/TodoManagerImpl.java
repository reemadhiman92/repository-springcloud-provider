package com.ikea.tc.todo.manager;

import com.ikea.tc.todo.model.Todo;
import com.ikea.tc.todo.repository.TodoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class TodoManagerImpl implements TodoManager {

    private static final Logger logger = LoggerFactory.getLogger(TodoManagerImpl.class);
    @Autowired
    private TodoRepository repository;

    @Override
    public Todo createTodo(String task) {
        return repository.save(new Todo(task));
    }

    @Override
    public Todo getTodo(Long id) throws TodoNotFoundException {
        Optional<Todo> optional = repository.findById(id);
        if (!optional.isPresent()) {
            throw new TodoNotFoundException("No todo found by supplied id: " + id);
        }

        Todo todo = optional.get();

        logger.debug("Created new TODO: " + todo);
        return todo;
    }

    @Override
    public List<Todo> findTodoByTask(String task) {
        return repository.findByTask(task);
    }

    @Override
    public void deleteTodo(Long id) {
        repository.deleteById(id);
        logger.debug("Deleted TODO found by id: " + id);
    }

    @Override
    public Todo updateTodo(Todo todo) {
        return repository.save(todo);
    }

    @Override
    public Page<Todo> getTodos(Integer page, Integer size) {
        return repository.findAll(PageRequest.of(page, size, Sort.unsorted()));
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public long getTodoCount() {
        return repository.count();
    }
}
