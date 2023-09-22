package com.SIMS.service;

import com.SIMS.model.dto.ResponseDto;
import com.SIMS.model.entity.Course;
import com.SIMS.model.entity.Profile;
import com.SIMS.repository.CourseRepository;
import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.UUID.randomUUID;

@Service
public class CourseService {
    CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public ResponseDto createCourse(Course course) throws Exception {
        course.setCourseId(randomUUID().toString());
        Course createdCourse;
        createdCourse = courseRepository.createCourse(course);
        if (createdCourse != null) {
            return new ResponseDto(HttpStatus.CREATED.toString(),"Course created successfully", createdCourse);
        } else {
            return new ResponseDto(HttpStatus.CREATED.toString(),"Course creation failed", null);
        }
    }

    public ResponseDto getAllCourses() {
        List<String> courses = courseRepository.getAllCourses();
        if (courses != null) {
            return new ResponseDto(HttpStatus.OK.toString(),"All courses found", courses);
        } else {
            return new ResponseDto(HttpStatus.OK.toString(),"All courses not found", null);
        }

    }

    public ResponseDto getCourseById(String courseId) {
        Course course = courseRepository.getCourseById(courseId);
        if (course != null) {
            return new ResponseDto(HttpStatus.OK.toString(),"course found", course);
        } else {
            return new ResponseDto(HttpStatus.OK.toString(),"course not found", null);
        }
    }

    public ResponseDto updateCourse(String courseId, Course course) throws Exception {
        course.setCourseId(courseId);
        Course updatedCourse = courseRepository.updateCourse(course);
        if (updatedCourse != null) {
            return new ResponseDto(HttpStatus.OK.toString(),"Course updated", updatedCourse);
        } else {
            return new ResponseDto(HttpStatus.OK.toString(),"Course not updated", null);
        }
    }

    public ResponseDto deleteCourse(String courseId) throws Exception {
        DeleteResult deleteResult = courseRepository.deleteCourse(courseId);
        if (deleteResult != null) {
            return new ResponseDto(HttpStatus.OK.toString(),"Course deleted", deleteResult);
        } else {
            return new ResponseDto(HttpStatus.OK.toString(),"Course not deleted", null);
        }
    }
}
