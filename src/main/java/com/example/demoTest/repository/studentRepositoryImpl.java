package com.example.demoTest.repository;

import com.example.demoTest.model.entity.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public class studentRepositoryImpl implements studentRepository{

    private final MongoTemplate mongoTemplate;

    @Autowired
    public studentRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    @Override
    public Profile saveProfile(Profile profile) {
        return mongoTemplate.insert(profile);
    }
}
