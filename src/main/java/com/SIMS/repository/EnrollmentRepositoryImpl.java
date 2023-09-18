package com.SIMS.repository;

import com.SIMS.model.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
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

    public Course updateCourse(Course existingCourse, Course course) throws Exception {
        Query query = new Query();
        query.addCriteria(Criteria.where("courseId").is(course.getCourseId()));
        Update update = new Update();
        update.set("Name",course.getName());
        if (course.getStudents() != null) {
            List<String> students = existingCourse.getStudents();
            for (String element : course.getStudents()) {
                if (!students.contains(element)) {
                    students.add(element);
                }
            }
            update.set("students",students);
        }
        FindAndModifyOptions findAndModifyOptions = new FindAndModifyOptions();
        findAndModifyOptions.returnNew(true);
        return mongoTemplate.findAndModify(query, update, findAndModifyOptions, Course.class);
    }
    @Override
    public Course enroll(String studentId, String courseId) throws Exception {
        Query query = new Query();
        query.addCriteria(Criteria.where("courseId").is(courseId));
        Course existingCourse =  mongoTemplate.findOne(query, Course.class);
        if (existingCourse != null) {
            List<String> students = existingCourse.getStudents();
            students.add(studentId);
            Course course = new Course(courseId, existingCourse.getName(), students);
            return updateCourse(existingCourse, course);
        } else {
            throw new Exception("Course not found");
        }
    }
}
