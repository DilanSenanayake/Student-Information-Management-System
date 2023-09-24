package com.SIMS.controller;

import com.SIMS.model.dto.CreateCourseDto;
import com.SIMS.model.entity.Course;
import com.SIMS.service.CourseService;
import com.SIMS.model.dto.ResponseDto;
import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping(value = "/courses")
    public ResponseDto<Course> createCourse(@Valid @RequestBody CreateCourseDto courseName) throws Exception {
        return courseService.createCourse(courseName);
    }

    @GetMapping(value = "/courses")
    public ResponseDto<List<String>> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping(value = "/courses/{courseId}")
    public ResponseDto<Course> getCourseById(@PathVariable String courseId) {
        return courseService.getCourseById(courseId);
    }

    @PutMapping(value = "/courses/{courseId}")
    public ResponseDto<Course> updateCourse(@PathVariable String courseId,
                                     @RequestBody Course course) throws Exception {
        return courseService.updateCourse(courseId, course);
    }

    @DeleteMapping(value = "/courses/{courseId}")
    public ResponseDto<DeleteResult> deleteStudent(@PathVariable String courseId) throws Exception {
        return courseService.deleteCourse(courseId);
    }

}

