package com.SIMS.controller;

import com.SIMS.model.entity.Course;
import com.SIMS.model.entity.Profile;
import com.SIMS.service.CourseService;
import com.SIMS.service.EnrollmentService;
import com.SIMS.service.StudentService;
import com.SIMS.model.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    @Autowired
    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping(value = "/students/{studentId}/enrollments/{courseId}")
    public ResponseDto enroll(@PathVariable String studentId, @PathVariable String courseId) throws Exception {
        return enrollmentService.enroll(studentId, courseId);
    }
}
