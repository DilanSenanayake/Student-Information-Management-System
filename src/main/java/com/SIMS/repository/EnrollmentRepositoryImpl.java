package com.SIMS.repository;

import com.SIMS.model.entity.Course;
import com.SIMS.model.entity.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EnrollmentRepositoryImpl implements EnrollmentRepository{
    private final MongoTemplate mongoTemplate;

    @Autowired
    public EnrollmentRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public String enroll(String studentId, String courseId) throws Exception {
        Query queryCourse = new Query();
        queryCourse.addCriteria(Criteria.where("courseId").is(courseId));
        if (mongoTemplate.exists(queryCourse, Course.class)) {
            Query queryStudent =  new Query();
            queryStudent.addCriteria(Criteria.where("studentId").is(studentId));
            Profile existingStudent =  mongoTemplate.findOne(queryStudent, Profile.class);
            if (existingStudent != null) {
                List<String> courses = existingStudent.getCourses();
                if (!courses.contains(courseId)) {
                    Query updateQuery = new Query();
                    updateQuery.addCriteria(Criteria.where("courseId").is(courseId));
                    Update update = new Update().push("students", studentId);
                    mongoTemplate.updateFirst(updateQuery, update, Course.class);

                    // update student's course list
                    Query studentQuery = new Query();
                    studentQuery.addCriteria(Criteria.where("studentId").is(studentId));
                    Update updateStudent = new Update().push("courses", courseId);
                    mongoTemplate.updateFirst(studentQuery, updateStudent, Profile.class);
                    return "Enrolled successfully.";
                } else {
                    throw new Exception("Student already enrolled");
                }
            } else {
                throw new Exception("Student not found");
            }
        } else {
            throw new Exception("Course not found");
        }
    }
}
