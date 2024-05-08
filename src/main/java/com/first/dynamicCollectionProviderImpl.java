package com.first;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;


@Service("configRepositoryCustom")
public class dynamicCollectionProviderImpl implements dynamicCollectionProvider {
    private final MongoTemplate mongoTemplate;
    private final String collectionName = "todo";

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
