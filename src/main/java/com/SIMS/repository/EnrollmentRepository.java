package com.SIMS.repository;

import java.util.List;

public interface EnrollmentRepository {
    String enroll(String studentId, String courseId) throws Exception;
    List<String> coursesEnrolledByStudent(String studentId) throws Exception;
    List<String> studentsEnrolledToCourse(String courseId) throws Exception;
    String unEnroll(String studentId, String courseId) throws Exception;
}
