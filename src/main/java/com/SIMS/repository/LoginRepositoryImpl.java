package com.SIMS.repository;

import com.SIMS.model.entity.LoginCredentials;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class LoginRepositoryImpl implements LoginRepository{
    private final MongoTemplate mongoTemplate;

    public LoginRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public LoginCredentials getUserbyEmail(String email) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));
        return mongoTemplate.findOne(query, LoginCredentials.class);
    }

    @Override
    public LoginCredentials signin(LoginCredentials signinCredentials) {
        return mongoTemplate.insert(signinCredentials);
    }
}
