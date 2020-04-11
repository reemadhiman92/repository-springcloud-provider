package com.ikea.tc.todo.repository;

import com.ikea.tc.todo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findByTask(String task);
}
