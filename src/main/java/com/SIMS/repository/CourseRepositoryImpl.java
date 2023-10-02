package com.SIMS.repository;

import com.SIMS.model.entity.Course;
import com.SIMS.model.entity.Profile;
import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class CourseRepositoryImpl implements CourseRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public CourseRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    @Override
    public Course createCourse(Course course) {
        return mongoTemplate.insert(course);
    }
    @Override
    public List<String> getAllCourses() {
        Query query = new Query();
        List<Course> courses = mongoTemplate.find(query, Course.class);

        return courses.stream()
                .map(Course::getName)
                .toList();
    }

    @Override
    public Course getCourseById(String courseId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("courseId").is(courseId));
        return  mongoTemplate.findOne(query, Course.class);
    }

    @Override
    public Course updateCourse(Course course) throws Exception {
        Course existingCourse = getCourseById(course.getCourseId());
        if (existingCourse == null) {
            throw new Exception("Course not found");
        } else {
            Query query = new Query();
            query.addCriteria(Criteria.where("courseId").is(course.getCourseId()));
            Update update = new Update();
            update.set("Name",course.getName());
            if (course.getStudents() != null) {
                if (existingCourse.getStudents() != null) {
                    List<String> students = existingCourse.getStudents();
                    for (String studentId : course.getStudents()) {
                        if (!students.contains(studentId)) {
                            students.add(studentId);
                        }
                    }
                    update.set("students",students);
                } else {
                    List<String> students = new ArrayList<>(course.getStudents());
                    update.set("students",students);
                }
                // update student's course list
                for (String studentId : course.getStudents()) {
                    // to do: check for already exist students
                    Query queryStudent = new Query();
                    queryStudent.addCriteria(Criteria.where("studentId").is(studentId));
                    Profile existingStudent = mongoTemplate.findOne(queryStudent, Profile.class);
                    assert existingStudent != null;
                    if (existingStudent.getCourses() != null) {
                        if (!existingStudent.getCourses().contains(course.getCourseId())) {
                            Query updateQuery = new Query();
                            updateQuery.addCriteria(Criteria.where("studentId").is(studentId));
                            Update updateStudent = new Update().push("courses", course.getCourseId());
                            mongoTemplate.updateFirst(updateQuery, updateStudent, Profile.class);
                        }
                    } else {
                        Query updateQuery = new Query();
                        updateQuery.addCriteria(Criteria.where("studentId").is(studentId));
                        Update updateStudent = new Update().push("courses", studentId);
                        mongoTemplate.updateFirst(updateQuery, updateStudent, Profile.class);
                    }
                }
            }
            FindAndModifyOptions findAndModifyOptions = new FindAndModifyOptions();
            findAndModifyOptions.returnNew(true);
            return mongoTemplate.findAndModify(query, update, findAndModifyOptions, Course.class);
        }
    }

    @Override
    public DeleteResult deleteCourse(String courseId) throws Exception {
        Course existingCourse = getCourseById(courseId);
        Query query = new Query();
        query.addCriteria(Criteria.where("courseId").is(courseId));
        if (existingCourse != null) {
            return mongoTemplate.remove(query, Course.class);
        }
        throw new Exception("Course not found");
    }
}
