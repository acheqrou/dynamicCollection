package com.first;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final MongoTemplate mongoTemplate;


    @Autowired
    public TodoService(TodoRepository todoRepository, MongoTemplate mongoTemplate) {
        this.todoRepository = todoRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public Todo save(Todo todo) {
        return todoRepository.save(todo);
    }

    public List<Todo> findAll() {
        return todoRepository.findAll();
    }


    public void createNewCollection(String collectionName) {
        if (!mongoTemplate.collectionExists(collectionName)) {
            mongoTemplate.createCollection(collectionName);
            System.out.println("Collection created: " + collectionName);
        } else {
            System.out.println("Collection already exists: " + collectionName);
        }
    }
}
