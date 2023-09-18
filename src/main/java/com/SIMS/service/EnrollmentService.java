package com.SIMS.service;

import com.SIMS.model.entity.Course;
import com.SIMS.model.entity.Profile;
import com.SIMS.model.dto.ResponseDto;
import com.SIMS.model.entity.Student;
import com.SIMS.repository.EnrollmentRepository;
import com.SIMS.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import static java.util.UUID.randomUUID;

@Service
public class EnrollmentService {
    EnrollmentRepository enrollmentRepository;

    @Autowired
    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    public ResponseDto enroll(String studentId, String courseId) throws Exception {
        Course enrolledCourse = enrollmentRepository.enroll(studentId, courseId);
        if (enrolledCourse != null) {
            return new ResponseDto(HttpStatus.OK.toString(),"Enrolled successfully", enrolledCourse);
        } else {
            throw new Exception("Enroll Failed");
        }
    }
}
