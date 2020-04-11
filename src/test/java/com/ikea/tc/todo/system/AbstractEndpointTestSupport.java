package com.ikea.tc.todo.system;

import com.ikea.tc.todo.TodoApplication;
import com.ikea.tc.todo.AbstractTestSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("e2e")
@SpringBootTest(classes = TodoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public abstract class AbstractEndpointTestSupport extends AbstractTestSupport {
    @Autowired
    protected TestRestTemplate template;

}
