package com.SIMS.repository;

import com.SIMS.exception.EntityAlreadyExistsException;
import com.SIMS.exception.EntityNotFoundException;
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
                if (courses != null) {
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
                    }
                    throw new EntityAlreadyExistsException("Student already enrolled", existingStudent);
                } else {
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
                }
            }
            throw new EntityNotFoundException("Student not found");
        }
        throw new EntityNotFoundException("Course not found");
    }

    @Override
    public List<String> coursesEnrolledByStudent(String studentId) throws Exception {
        Query query = new Query();
        query.addCriteria(Criteria.where("studentId").is(studentId));
        Profile student = mongoTemplate.findOne(query, Profile.class);
        if (student != null) {
            return student.getCourses();
        }
        throw new EntityNotFoundException("Student not found");
    }

    @Override
    public List<String> studentsEnrolledToCourse(String courseId) throws Exception {
        Query query = new Query();
        query.addCriteria(Criteria.where("courseId").is(courseId));
        Course course = mongoTemplate.findOne(query, Course.class);
        if (course != null) {
            return course.getStudents();
        }
        throw new EntityNotFoundException("Course not found");
    }

    @Override
    public String unEnroll(String studentId, String courseId) throws Exception {
        Query queryCourse = new Query();
        queryCourse.addCriteria(Criteria.where("courseId").is(courseId));
        if (mongoTemplate.exists(queryCourse, Course.class)) {
            Query queryStudent =  new Query();
            queryStudent.addCriteria(Criteria.where("studentId").is(studentId));
            Profile existingStudent =  mongoTemplate.findOne(queryStudent, Profile.class);
            if (existingStudent != null) {
                List<String> courses = existingStudent.getCourses();
                if (courses != null) {
                    if (courses.contains(courseId)) {
                        Query updateQuery = new Query();
                        updateQuery.addCriteria(Criteria.where("courseId").is(courseId));
                        Update update = new Update().pull("students", studentId);
                        mongoTemplate.updateFirst(updateQuery, update, Course.class);

                        // update student's course list
                        Query studentQuery = new Query();
                        studentQuery.addCriteria(Criteria.where("studentId").is(studentId));
                        Update updateStudent = new Update().pull("courses", courseId);
                        mongoTemplate.updateFirst(studentQuery, updateStudent, Profile.class);
                        return "UnEnrolled successfully.";
                    }
                    throw new EntityNotFoundException("Student already unenrolled");
                }
                throw  new EntityNotFoundException("Student not enrolled any courses");
            }
            throw new EntityNotFoundException("Student not found");
        }
        throw new EntityNotFoundException("Course not found");
    }
}
