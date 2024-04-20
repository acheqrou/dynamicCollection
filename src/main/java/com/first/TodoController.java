package com.first;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.mongodb.core.query.Query;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;


@RestController
@RequestMapping("/todos")
public class TodoController {
    private static final Logger logger = LoggerFactory.getLogger(TodoController.class);
    private final TodoService todoService;

    private final MongoTemplate mongoTemplate;

private dynamicCollectionProvider provider;
    private boolean completed;

    @Autowired
    public TodoController(TodoService todoService, MongoTemplate mongoTemplate, dynamicCollectionProvider provider) {
        this.todoService = todoService;
        this.mongoTemplate = mongoTemplate;
        this.provider=provider;


    }


    @GetMapping
    public List<Todo> getAllTodos() {

        return todoService.findAll();
    }

    @GetMapping("total")
    public int getAllTodosT() {

        return todoService.findAll().size();
    }

    public List<Todo> findAllInCollection(String collectionName) {
        Query query = new Query(); // Create an empty query
        List<Todo> todos = mongoTemplate.find(query, Todo.class, collectionName);
        return todos;
    }
    @PostMapping
    //@SanitizeInput
    public Todo addTodo(@RequestBody Todo todo, @RequestParam("name") String name ) {
        logger.info("This is an informational message :: {}", name);
        return todoService.save(todo);
    }




    @PutMapping("/collection-name")
    public void setCollectionName(@RequestParam String name) {
        long uu = Instant.now().toEpochMilli();
        System.out.println("epoch           : "+uu);
        String newCollectionName = "todos"+uu;
        String lagacyName = this.provider.getCollectionName();

        this.todoService.createNewCollection(newCollectionName);
        var todos = getAllTodos();
        System.out.println("Inserting data in progress ...");
        this.mongoTemplate.insert(todos, newCollectionName);
        System.out.println("Inserting data done !!");

        List<Todo> todoss = new ArrayList<>();
        for(int i=0; i<750000;i++){
            var todo = new Todo();
            boolean completed = i % 2==0;
            todo.setCompleted(completed);
            todo.setId("ks!"+i);
            todo.setTitle("ks!"+i);
            todoss.add(todo);
        }
        System.out.println("Inserting data in progress ...");
    try {
        this.mongoTemplate.insert(todoss, newCollectionName);
        System.out.println("Inserting data done !!");

        System.out.println("call set collection name ....");
        this.provider.setCollectionNames(newCollectionName);
        System.out.println("drop legacy collection ...........");
        this.mongoTemplate.dropCollection(lagacyName);
        System.out.println("dropped !!!!!!");

    }catch (Exception e){
        System.out.println(" Error Inserting ........");
        this.mongoTemplate.dropCollection(newCollectionName);

    }



    }


}

