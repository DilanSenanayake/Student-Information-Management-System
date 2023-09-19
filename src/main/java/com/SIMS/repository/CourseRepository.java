package com.SIMS.repository;

import com.SIMS.model.entity.Course;
import com.mongodb.client.result.DeleteResult;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository {
    Course createCourse(Course course);
    List<String> getAllCourses();
    Course getCourseById(String studentId);

    Course updateCourse(Course course) throws Exception;

    DeleteResult deleteCourse(String courseId) throws Exception;
}
