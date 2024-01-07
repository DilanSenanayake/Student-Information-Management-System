package com.SIMS.controller;

import com.SIMS.service.EnrollmentService;
import com.SIMS.model.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins = "*")
@Tag(name = "Enrollment", description = "Endpoint for enrollments")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    @Autowired
    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping(value = "/students/{studentId}/enrollments/{courseId}")
    @Operation(summary = "Enroll a student to a course", description = "Enroll a student to a course")
    public ResponseDto<String> enroll(@PathVariable String studentId, @PathVariable String courseId) throws Exception {
        return enrollmentService.enroll(studentId, courseId);
    }
    @GetMapping(value = "/students/{studentId}/enrollments")
    @Operation(summary = "Courses enrolled by a student", description = "Courses enrolled by a student")
    public ResponseDto<List<String>> coursesEnrolledByStudent(@PathVariable String studentId) throws Exception {
        return enrollmentService.coursesEnrolledByStudent(studentId);
    }
    @GetMapping(value = "/courses/{courseId}/enrollments")
    @Operation(summary = "Students enrolled in a course", description = "Students enrolled in a course")
    public ResponseDto<List<String>> studentsEnrolledToCourse(@PathVariable String courseId) throws Exception {
        return enrollmentService.studentsEnrolledToCourse(courseId);
    }
    @DeleteMapping(value = "/students/{studentId}/enrollments/{courseId}")
    @Operation(summary = "Unroll a student", description = "Unroll a student")
    public ResponseDto<String> unEnroll(@PathVariable String studentId, @PathVariable String courseId) throws Exception {
        return enrollmentService.unEnroll(studentId, courseId);
    }
}
