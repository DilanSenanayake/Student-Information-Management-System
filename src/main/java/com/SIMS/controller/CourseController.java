package com.SIMS.controller;

import com.SIMS.model.entity.Course;
import com.SIMS.model.entity.Profile;
import com.SIMS.service.CourseService;
import com.SIMS.service.StudentService;
import com.SIMS.model.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping(value = "/courses")
    public ResponseDto createCourse(@Valid @RequestBody Course course) throws Exception {
        return courseService.createCourse(course);
    }

    @GetMapping(value = "/courses")
    public ResponseDto getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping(value = "/courses/{courseId}")
    public ResponseDto getCourseById(@PathVariable String courseId) {
        return courseService.getCourseById(courseId);
    }

    @PutMapping(value = "/courses/{courseId}")
    public ResponseDto updateCourse(@PathVariable String courseId,
                                     @RequestBody Course course) throws Exception {
        return courseService.updateCourse(courseId, course);
    }

    @DeleteMapping(value = "/courses/{courseId}")
    public ResponseDto deleteStudent(@PathVariable String courseId) throws Exception {
        return courseService.deleteCourse(courseId);
    }

}

