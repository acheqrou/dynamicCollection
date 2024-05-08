package com.first;

import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service("configRepositoryCustom")
public class dynamicCollectionProviderImpl implements dynamicCollectionProvider  {
    private  String collectionName = "todo";
    private final MongoTemplate mongoTemplate;

    public dynamicCollectionProviderImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public String getCollectionName(String prefix) {
            return mongoTemplate.getCollectionNames()
                    .stream()
                    .filter(name -> name.startsWith(prefix))
                    .sorted().peek(System.out::println)
                    .toList().getFirst();
    }
}
