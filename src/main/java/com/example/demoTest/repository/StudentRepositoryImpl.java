package com.example.demoTest.repository;

import com.example.demoTest.model.entity.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;


@Repository
public class StudentRepositoryImpl implements StudentRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public StudentRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    @Override
    public Profile saveProfile(Profile profile) {
        return mongoTemplate.insert(profile);
    }

    @Override
    public Profile getProfileByEmail(String email) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));
        return  mongoTemplate.findOne(query, Profile.class);
    }
}
