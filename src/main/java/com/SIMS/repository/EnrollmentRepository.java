package com.SIMS.repository;

import com.SIMS.model.entity.Course;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository {
    String enroll(String studentId, String courseId) throws Exception;
    List<String> coursesEnrolledByStudent(String studentId) throws Exception;
    List<String> studentsEnrolledToCourse(String courseId) throws Exception;
    String unEnroll(String studentId, String courseId) throws Exception;
}
