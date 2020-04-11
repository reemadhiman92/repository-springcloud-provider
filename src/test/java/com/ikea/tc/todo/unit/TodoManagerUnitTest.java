package com.ikea.tc.todo.unit;

import com.ikea.tc.todo.manager.TodoManager;
import com.ikea.tc.todo.manager.TodoManagerImpl;
import com.ikea.tc.todo.manager.TodoNotFoundException;
import com.ikea.tc.todo.model.Todo;
import com.ikea.tc.todo.repository.TodoRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TodoManagerUnitTest extends AbstractUnitTestSupport {
    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoManager todoManager = new TodoManagerImpl();

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createTodo() {
        todoManager.createTodo("Start the build");
        verify(todoRepository).save(any(Todo.class));
    }

    @Test
    public void getTodo() throws TodoNotFoundException {
        Long id = 1l;
        Optional<Todo> optional = Optional.of(mock(Todo.class));
        when(todoRepository.findById(id)).thenReturn(optional);

        todoManager.getTodo(id);
        verify(todoRepository).findById(id);
    }

    @Test
    public void findTodoByTask() {
        String task = UUID.randomUUID().toString();
        todoManager.findTodoByTask(task);
        verify(todoRepository).findByTask(task);
    }

    @Test
    public void deleteTodo() {
        Long id = 1l;
        todoManager.deleteTodo(id);
        verify(todoRepository).deleteById(id);
    }

    @Test
    public void getTodos() {
        todoManager.getTodos(0, 20);
        verify(todoRepository).findAll(any(PageRequest.class));
    }

    @Test
    public void deleteAll() {
        todoManager.deleteAll();
        verify(todoRepository).deleteAll();
    }

    @Test
    public void getTodoCount() {
        todoManager.getTodoCount();
        verify(todoRepository).count();
    }
}
