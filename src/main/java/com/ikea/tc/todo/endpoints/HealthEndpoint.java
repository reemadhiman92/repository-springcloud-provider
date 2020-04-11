package com.ikea.tc.todo.endpoints;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping(value = HealthEndpoint.ENDPOINT)
public class HealthEndpoint {
    public static final String ENDPOINT = "/health";
    private static final Logger LOGGER = LoggerFactory.getLogger(HealthEndpoint.class);

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> healthCheck() {
        String msg = "{\"message\":" + new Date().getTime() + "}";
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
}
