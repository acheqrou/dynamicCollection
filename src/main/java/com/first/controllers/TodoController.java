package com.first.controllers;

import com.first.SanitizeInput;
import com.first.SanitizedLogger;
import com.first.Todo;
import com.first.TodoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/todos")
public class TodoController {
    private static final Logger logger = LoggerFactory.getLogger(TodoController.class);
    SanitizedLogger sanitizedLogger = new SanitizedLogger(TodoController.class);
    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }



    @GetMapping
    public List<Todo> getAllTodos() {

        return todoService.findAll();
    }


    @PostMapping
    //@SanitizeInput
    public Todo addTodo(@RequestBody Todo todo, @RequestParam("name") String name ) {
        logger.info("This is an informational message :: {}", name);
        return todoService.save(todo);
    }

}

