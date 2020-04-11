package com.ikea.tc.todo.contract;

import static org.mockito.ArgumentMatchers.anyLong;
import org.springframework.beans.factory.annotation.Autowired;
import static org.mockito.BDDMockito.given;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito.BDDMyOngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ikea.tc.todo.TodoApplication;
import com.ikea.tc.todo.endpoints.TodoEndpoint;
import com.ikea.tc.todo.manager.TodoManager;
import com.ikea.tc.todo.manager.TodoManagerImpl;
import com.ikea.tc.todo.manager.TodoNotFoundException;
import com.ikea.tc.todo.model.Todo;
import com.ikea.tc.todo.repository.TodoRepository;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
@RunWith(SpringRunner.class)
@WebMvcTest(TodoApplication.class)
public class ContractTest {

	    @Autowired
	    private TodoManagerImpl todoManager;
		Todo todo_object = new Todo((long)1, "Pay bills.");
		
		@Test
		public void setup() {
		try {
			given(todoManager.getTodo(anyLong())).willReturn(todo_object);
		} catch (TodoNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			RestAssuredMockMvc.standaloneSetup(todoManager);
		} 
}
