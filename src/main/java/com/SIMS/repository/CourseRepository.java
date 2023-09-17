package com.SIMS.repository;

import com.SIMS.model.entity.Course;
import com.SIMS.model.entity.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository {
    Course createCourse(Course course);
    List<String> getAllCourses();
    Course getCourseById(String studentId);
    Course updateCourse(Course course) throws Exception;
    Course deleteCourse(String courseId) throws Exception;
}
