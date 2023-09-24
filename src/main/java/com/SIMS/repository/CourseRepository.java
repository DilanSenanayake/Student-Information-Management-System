package com.SIMS.repository;

import com.SIMS.model.entity.Course;
import com.mongodb.client.result.DeleteResult;

import java.util.List;


public interface CourseRepository {
    Course createCourse(Course course);
    List<String> getAllCourses();
    Course getCourseById(String studentId);

    Course updateCourse(Course course) throws Exception;

    DeleteResult deleteCourse(String courseId) throws Exception;
}
