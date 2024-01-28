package com.SIMS.repository;

import com.SIMS.exception.EntityNotFoundException;
import com.SIMS.model.entity.Profile;
import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;


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

    @Override
    public List<Profile> getAllStudents() throws Exception {
        Query query = new Query();
        List<Profile> studentProfiles = mongoTemplate.find(query, Profile.class);
        if (studentProfiles.size() != 0) {
            return studentProfiles;
        }
        throw new EntityNotFoundException("No students found");

    }

    @Override
    public Profile getStudentById(String studentId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("studentId").is(studentId));
        return  mongoTemplate.findOne(query, Profile.class);
    }

    @Override
    public Profile updateProfile(Profile profile) throws Exception {
        Profile existingProfile = getStudentById(profile.getStudentId());
        Query query = new Query();
        query.addCriteria(Criteria.where("studentId").is(profile.getStudentId()));
        Update update = new Update();
        update.set("firstName",profile.getFirstName());
        update.set("lastName", profile.getLastName());
        update.set("email", profile.getEmail());
        if (existingProfile != null) {
            FindAndModifyOptions findAndModifyOptions = new FindAndModifyOptions();
            findAndModifyOptions.returnNew(true);
            return mongoTemplate.findAndModify(query, update, findAndModifyOptions, Profile.class);
        }
        throw new EntityNotFoundException("Student not found");
    }

    @Override
    public DeleteResult deleteStudent(String studentId) throws Exception {
        Profile existingProfile = getStudentById(studentId);
        Query query = new Query();
        query.addCriteria(Criteria.where("studentId").is(studentId));
        if (existingProfile != null) {
            return mongoTemplate.remove(query, Profile.class);
        }
        throw new EntityNotFoundException("Student not found");
    }
}
