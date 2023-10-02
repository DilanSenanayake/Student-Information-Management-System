package com.SIMS.service;

import com.SIMS.model.dto.ResponseDto;
import com.SIMS.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {
    EnrollmentRepository enrollmentRepository;

    @Autowired
    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    public ResponseDto<String> enroll(String studentId, String courseId) throws Exception {
        String enrolledCourse = enrollmentRepository.enroll(studentId, courseId);
        if (enrolledCourse != null) {
            return new ResponseDto<>(HttpStatus.OK.toString(),"Enrolled successfully", enrolledCourse);
        }
        return new ResponseDto<>(HttpStatus.OK.toString(),"Enroll failed", null);
    }
    public ResponseDto<List<String>> coursesEnrolledByStudent(String studentId) throws Exception{
        List<String> courses = enrollmentRepository.coursesEnrolledByStudent(studentId);
        if (courses != null) {
            return new ResponseDto<>(HttpStatus.OK.toString(),"Courses found", courses);
        }
        return new ResponseDto<>(HttpStatus.OK.toString(),"Courses not found", null);
    }
    public ResponseDto<List<String>> studentsEnrolledToCourse(String coursetId) throws Exception{
        List<String> students = enrollmentRepository.studentsEnrolledToCourse(coursetId);
        if (students != null) {
            return new ResponseDto<>(HttpStatus.OK.toString(),"Students found", students);
        }
        return new ResponseDto<>(HttpStatus.OK.toString(),"Students not found", null);
    }
    public ResponseDto<String> unEnroll(String studentId, String courseId) throws Exception {
        String unEnrolledCourse = enrollmentRepository.unEnroll(studentId, courseId);
        if (unEnrolledCourse != null) {
            return new ResponseDto<>(HttpStatus.OK.toString(),"Unenrolled successfully", unEnrolledCourse);
        }
        return new ResponseDto<>(HttpStatus.OK.toString(),"Unenroll failed", null);
    }

}
