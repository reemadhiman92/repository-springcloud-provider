package com.ikea.tc.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@SpringBootApplication
//@ComponentScan
@RestController
@ImportResource("classpath:applicationContext.xml")
public class TodoApplication {

    @RequestMapping("/")
    public String root() {
        return "{\"time\":" + new Date().getTime() + "}"; //endpoint for service aliveness check
    }


    @RequestMapping("/api")
    public String apiRoot() {
        return "{\"time\":" + new Date().getTime() + "}"; //endpoint for service aliveness check
    }


    public static void main(String[] args) {
        SpringApplication.run(TodoApplication.class, args);
    }
}
