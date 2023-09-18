package com.SIMS.repository;

import com.SIMS.model.entity.Course;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository {
    Course enroll(String studentId, String courseId) throws Exception;
}
