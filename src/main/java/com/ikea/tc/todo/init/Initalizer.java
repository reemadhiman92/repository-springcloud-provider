package com.ikea.tc.todo.init;

import com.ikea.tc.todo.manager.TodoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
public class Initalizer implements CommandLineRunner {
    @Autowired
    private TodoManager todoManager;

    @Override
    public void run(String... args) throws Exception {
        clearAll();
        createTodos();
    }

    private void createTodos() {
        todoManager.createTodo("Pay bills.");
        todoManager.createTodo("Do grocery.");
        todoManager.createTodo("Call Mom.");
    }

    private void clearAll() {
        todoManager.deleteAll();
    }
}
