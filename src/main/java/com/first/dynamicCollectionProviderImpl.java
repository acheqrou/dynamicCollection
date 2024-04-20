package com.first;

import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component("configRepositoryCustom")
public class dynamicCollectionProviderImpl implements dynamicCollectionProvider  {
    private  String collectionName = "todo";
    private final MongoTemplate mongoTemplate;

    public dynamicCollectionProviderImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public String getCollectionName() {
       String defaultName = getCollectionsByPrefix("todos");
        System.out.println();
        System.out.println("coll name : "+defaultName);
        System.out.println();
        return defaultName;
    }

    @Override
    public  void setCollectionNames(String collectionName) {
        this.collectionName = collectionName;

    }

    public String getCollectionsByPrefix(String prefix) {
        // Query for collection names based on prefix

        return mongoTemplate.getCollectionNames()
                .stream()
                .filter(name -> name.startsWith(prefix))
                .sorted().peek(System.out::println)
                .toList().getFirst();
    }
}
